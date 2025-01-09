use std::{any::Any, collections::HashMap, hash::Hash};

fn main() {
    println!("Hello, world!");
}



// trait EqHash : Eq + Hash{}
// // impl EqHash for 
// struct Val<T>{

// }
//use serde for String values to convert back into intended type
type Arg = (String, String);
struct DynamicFunction<R>{
    method: Box<dyn FnMut(Vec<Arg>) -> R>,
    args: Vec<String>,
    storage: HashMap<Vec<Arg>, R>
}
impl<R> DynamicFunction<R>{
    fn new(
        method: Box<dyn FnMut(Vec<Arg>) -> R>,
        args: Vec<String>,
    ) -> Self{
        Self { method, args, storage: HashMap::new() }
    }

    // fn access(&self, args: Vec<Arg>) -> R{
    //     // if let Some(r) = 
    //     // self.storage.
    //     todo!()
    // }

    // fn call_and_store(&mut self, inp: Vec<String>){
        
    //     if let Some(r) = self.storage.get() {

    //     }
    // }
}