use std::{ops::Deref, rc::Rc, sync::{Arc, RwLock, RwLockReadGuard, RwLockWriteGuard}};

fn main() {
    println!("Hello, world!");
}



type R<'a, T> = RwLockReadGuard<'a, T>;
type M<'a, T> = RwLockWriteGuard<'a, T>;

struct JRef<T>{
    inner: Rc<RwLock<T>>
}
impl<T> JRef<T>{
    fn new(val: T) -> Self{
        Self { inner: Rc::new(RwLock::new(val)) }
    }
    fn val(&self) -> Option<RwLockReadGuard<T>>{
        self.inner.read().ok()
    }
    fn set(&mut self, val: T){
        self.inner = Rc::new(RwLock::new(val));
    }
    fn fval(&self) -> R<T>{
        self.inner.read().unwrap()
    }
}
impl<T> Clone for JRef<T>{
    fn clone(&self) -> Self {
        Self { inner: self.inner.clone() }
    }
}




type BST<T> = BinarySearchTree<T>;

struct BinarySearchTree<T: Ord>{
    value: JRef<T>,
    left: Option<JRef<Self>>,
    right: Option<JRef<Self>>,
}
impl<T: Ord> BinarySearchTree<T>{
    fn new(val: T) -> Self{
        Self { value: JRef::new(val), left: None, right: None }
    }
    fn get(&self, num: T) -> Option<R<T>>{
        let selection = if num < *self.value.fval() { self.left.clone() } else { self.right.clone() };
        if let Some(selection) = selection{
            // selection.fval().get(num)
            todo!()
        } else{
            None
        }
    }
    fn size(&self) -> usize{
        1 + self.left.as_ref().map(|x| x.fval().size()).unwrap_or(0) + self.right.as_ref().map(|x| x.fval().size()).unwrap_or(0)
    }
    fn insert(&mut self, num: T){

        let selection = if num < *self.value.fval() { self.left.clone() } else { self.right.clone() };
        if let Some(selection) = selection{
            // selection.fval().get(num)
            todo!()
        } else{
            None
        }
        let bst = BST::new(num);
    }
}



