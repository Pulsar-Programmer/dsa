use std::{any::Any, collections::HashMap};

fn main() {
    println!("Hello, world!");
}






type Arg = (String, Box<dyn Any>);
struct DynamicFunction<R>{
    method: Box<dyn FnMut(Vec<Arg>) -> R>,
    args: Vec<Arg>,
    storage: HashMap<Vec<Arg>, R>
}
impl<R> DynamicFunction<R>{
    fn new(
        method: Box<dyn FnMut(Vec<Arg>) -> R>,
        args: Vec<Arg>,
    ) -> Self{
        Self { method, args, storage: HashMap::new() }
    }

    fn access(&self, args: Vec<Arg>) -> R{
        // if let Some(r) = self.storage
        todo!()
    }
    fn call_and_store(){
        todo!()
    }
}