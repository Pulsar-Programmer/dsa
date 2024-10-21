use std::{hash::Hash, marker::PhantomData};

fn main() {
    println!("Hello, world!");
}



struct HashBrown<K, V>{
    data: PhantomData<Entry<K,V>>,
}
impl<K,V> HashBrown<K,V>{
    pub fn put(e: Entry<K,V>){
        todo!()
    }
}


struct Entry<K, V>{
    key: K,
    value: V,
}