use std::{mem::MaybeUninit, ops::Deref};

fn main() {
    println!("Hello, world!");
}


struct CustomVec<T> {
    capacity: usize,
    size: usize,
    data: *mut T,
}
impl<T> CustomVec<T>{
    
}