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
    pub fn get(&self, key: K) -> Option<V>{
        let mut index = hash(&key) as usize % self.entries.len();
        
        if self.entries[index].is_none() {
            return None;
        }

        while let Some(entry) = &self.entries[index]{
            if entry.key == key{
                return Some(entry.value.clone());
            }
            index += 1;
            index %= self.entries.len();
        }
        None
    }
    pub fn with_capacity(cap: usize) -> Self{
        let mut vec = Vec::new();
        for i in 0..cap{
            vec.push(None);
        }
        Self { entries : vec }
    }
}



struct Entry<K, V>{
    key: K,
    value: V,
}