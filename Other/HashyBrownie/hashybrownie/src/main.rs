use std::{hash::{DefaultHasher, Hash, Hasher}, marker::PhantomData};

fn main() {

}

fn hash(val: & impl Hash) -> u64 {
    let mut hasher = DefaultHasher::new();
    val.hash(&mut hasher);
    hasher.finish()
}

//look into removing the V:Clone bound - is it necessary?
struct HashBrown<K: Hash + Eq, V: Clone>{
    entries: Vec<Option<Entry<K,V>>>
}
impl<K: Hash + Eq,V: Clone> HashBrown<K,V>{
    pub fn put(&mut self, e: Entry<K,V>){
        // let Entry { key, value } = e;
        let mut index = hash(&e.key) as usize % self.entries.len();
        while let Some(entry) = &mut self.entries[index] {
            if entry.key == e.key{
                entry.value = e.value.clone();
            }
            //linear probing
            index += 1;
            index %= self.entries.capacity();
        }
        //check for reahashing
        self.entries[index] = Some(e);
    }
    // pub fn get(&self, key: K) -> Option<V>{
    //     let mut index = hash(&key) as usize % self.entries.len();
        
    //     if self.entries[index].is_none() {
    //         return None;
    //     }

    //     while let Some(entry) = &self.entries[index]{
    //         if entry.key == key{
    //             return Some(entry.value.clone());
    //         }
    //         index += 1;
    //         index %= self.entries.len();
    //     }
    //     None
    // }
    // pub fn with_capacity(cap: usize) -> Self{
    //     let mut vec = Vec::new();
    //     for i in 0..cap{
    //         vec.push(None);
    //     }
    //     Self { entries : vec }
    // }
}



struct Entry<K, V>{
    key: K,
    value: V,
}











fn linear_probing(index: usize) -> usize{
    index + 1
}
// fn quadratic_probing(index: usize) -> usize{
    
// }



enum InsertResult{
    Ok,
    ContentFull,
}

//there are so many ways to implement a HashMap ... how can we make it as modular as possible

///A HashMap which cannot rehash and can reach max capacity.
struct CappedHashMap<K,V>{
    next: Box<dyn FnMut(usize) -> usize>,
    hasher: Box<dyn FnMut(K) -> usize>,
    entries: Vec<EntryContainer<Entry<K, V>>>,
}
impl<K,V> CappedHashMap<K,V>{
    fn with_capacity(capacity: usize, next_method: Box<dyn FnMut(usize) -> usize>, hasher: Box<dyn FnMut(K) -> usize>) -> Self{
        Self { next: next_method, hasher, entries: Vec::with_capacity(capacity) }
    }
}


enum EntryContainer<T>{
    Val(T),
    Tombstone,
    None,
}
// impl<T> EntryContainer<T>{
//     fn initialize(&mut self, k: K, v: V){
//         self = Val(())
//     }
// }
use EntryContainer::{Val, Tombstone, None};



struct LinearProbingHashMap<K: Hash, V>{
    entries: Vec<EntryContainer<Entry<K, V>>>,
}
impl<K: Hash + Eq, V> LinearProbingHashMap<K, V>{
    fn with_capacity(capacity: usize) -> Self{
        Self { entries: Vec::with_capacity(capacity) }
    }

    fn new() -> Self{
        Self { entries: Vec::new() }
    }

    fn attempt_rehash(&mut self){
        if self.entries.capacity() as f64 / self.entries.len() as f64 >= 0.75 {
            todo!()
        }
    }
    
    fn insert(&mut self, key: K, value: V){
        let mut index = hash(&key)  as usize % self.entries.capacity();
        loop{
            match self.entries.get(index){
                Some(entry) => {
                    match entry {
                        Val(..) => {
                            index += 1;
                            index %= self.entries.capacity();
                            continue
                        },
                        _ => {}
                    }
                },
                _ => {}
            }
            break;
        }
        self.entries[index] = Val(Entry{key, value});
        self.attempt_rehash();
    }

    fn get(&self, key: &K) -> Option<&V>{
        let mut index = hash(&key)  as usize % self.entries.capacity();
        loop{
            match self.entries.get(index){
                Some(entry) => {
                    match entry {
                        Val(entry) => {
                            if &entry.key == key{ //&& &entry.value == val  ADD THIS IF YOU NEED IT with param
                                return Some(&entry.value)
                            } else {
                                index += 1;
                                index %= self.entries.capacity();
                                continue
                            }
                        },
                        Tombstone => return Option::None,
                        None => return Option::None,
                        
                    }
                },
                _ => return Option::None
            }
        }
    }
    //NOTE: TOMBSTONE SHOULD KEEPS KEY - MAKE NOTE OF THIS
    fn remove(&mut self, key: K) -> Option<V>{
        let mut index = hash(&key)  as usize % self.entries.capacity();
        let mut val = Option::None;

        loop{
            match self.entries.get_mut(index){
                Some(entry) => {
                    match &entry{
                        Val(..) => {
                            val = {
                                let Val(val) = std::mem::replace(entry, Tombstone) else { return Option::None };
                                Some(val.value)
                            };
                        },
                        Tombstone => {
                            index += 1;
                            index %= self.entries.capacity();
                            continue
                        },
                        None => return Option::None,
                    }
                },
                _ => {}
            }
            break;
        }
        self.attempt_rehash();
        val
    }

}

impl<K: Hash,V> IntoIterator for LinearProbingHashMap<K,V>{
    type Item = Entry<K,V>;

    type IntoIter = <Vec<Self::Item> as IntoIterator>::IntoIter;

    fn into_iter(self) -> Self::IntoIter {
        self.entries.into_iter().filter_map(|val|match val {
            Val(v) => Some(v),
            _ => Option::None,
        }).collect::<Vec<Entry<K,V>>>().into_iter()
    }
}