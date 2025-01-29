use std::{any::Any, collections::HashMap, hash::Hash};

fn main() {
    println!("Hello, world!");
}



// trait EqHash : Eq + Hash{}
// // impl EqHash for 
// struct Val<T>{

// }
//use serde for String values to convert back into intended type
// type Arg = (String, String);
type Value = String;
struct DynamicFunction<R, const N: usize>{
    method: Box<dyn FnMut([Value; N]) -> R>,
    // args: Vec<String>,
    storage: HashMap<[Value; N], R>
}
impl<R, const N: usize> DynamicFunction<R, N>{
    fn new(
        method: Box<dyn FnMut([Value; N]) -> R>,
    ) -> Self{
        Self { method, storage: HashMap::new() }
    }

    // fn access(&self, args: Vec<Arg>) -> R{
    //     // if let Some(r) = 
    //     // self.storage.
    //     todo!()
    // }

    fn call_and_store(&mut self, inp: [Value; N]) -> &R{
        if let Some(r) = self.storage.get(&inp) {
            return r;
        }

        let r = (self.method)(inp);
        let b = &r;
        // self.storage.insert(inp, r);
        // b
        todo!()
    }
}