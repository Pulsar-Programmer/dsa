use std::{collections::HashMap, ops::{Add, Deref, DerefMut}};

fn main() {
    println!("Hello, world!");
}

//maybe add interior mutability to this
struct Snow<'a, T : SnowMerge>{
    inner: T,
    edits: Vec<&'a T>,
}
impl<'a, T: SnowMerge> Snow<'a, T>{
    ///Creates a new `SnowPtr`.
    fn new(t: T) -> Self{
        Self { inner: t, edits: Vec::new() }
    }
    ///Processes the incoming edits.
    fn process(&mut self){
        for edit in self.edits.drain(..){
            self.inner.merge(edit);
        }
    }
    ///Gets a ref to the inner value before edits are made.
    fn get(&self) -> &T{
        &self.inner
    }
    ///Gets a ref to the inner value after edits are made.
    fn get_process(&mut self) -> &T{
        self.process();
        &self.inner
    }
    ///Edits the value to a new value.
    fn edit_to(&mut self, new_value: &'a T){
        self.edits.push(new_value);
    }
    //maybe impl edit_to with COW<T>
    ///Gets a mutable ref to the inner value before edits are made.
    fn get_mut(&mut self) -> &mut T{
        &mut self.inner
    }
    ///Gets a mutable ref to the inner value after edits are made.
    fn get_mut_process(&mut self) -> &mut T{
        self.process();
        &mut self.inner
    }
}
impl<T: SnowMerge> Deref for Snow<'_, T>{
    type Target = T;

    fn deref(&self) -> &Self::Target {
        self.get()
    }
}
impl<T: SnowMerge> DerefMut for Snow<'_, T>{
    fn deref_mut(&mut self) -> &mut Self::Target {
        self.get_mut()
    }
}
// impl<T: SnowMerge> Drop for Snow<T>{
//     fn drop(&mut self) {
//         // todo!()
//     }
// }


// Do it with a merger

// type Merger = HashMap<String, Box<dyn std::any::Any>>;
// you can technically do it with a merger but..
//MOW - merge on write

trait SnowMerge<T = Self>{
    fn merge(&mut self, t: &T);
}



impl SnowMerge for String{
    fn merge<'a>(&'a mut self, t: &'a String) {
        self.push_str(t);
    }
}

#[test]
fn first_test(){
    let mut ptr = Snow::new(String::from("Hello"));
    let new = String::from(" world!");
    ptr.edit_to(&new);
    println!("{}", ptr.get_process());
}