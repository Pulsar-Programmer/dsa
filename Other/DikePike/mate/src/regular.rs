use std::{any::Any, collections::HashMap};

use crate::alternate;

pub struct JRef<T>{
    phantom: std::marker::PhantomData<T>,
    id: usize,
    // pool: *mut JRefPool,
}
impl<T> Clone for JRef<T> {
    fn clone(&self) -> Self {
        Self { phantom: self.phantom.clone(), id: self.id.clone() }
    }
}
impl<T> Copy for JRef<T> {}

pub struct JRefPool{
    pub pool: HashMap<usize, Box<dyn Any>>,
}
impl JRefPool{
    pub fn new() -> JRefPool{
        JRefPool{
            pool: HashMap::new(),
        }
    }
    pub fn insert<T: 'static>(&mut self, id: usize, value: T) -> JRef<T>{
        self.pool.insert(id, Box::new(value));
        JRef{
            phantom: std::marker::PhantomData,
            id,
            // pool: self as *mut JRefPool,
        }
    }
    pub fn insert_alternate<T: 'static>(&mut self, id: usize, value: T) -> alternate::JRef<T>{
        self.pool.insert(id, Box::new(value));
        alternate::JRef{
            phantom: std::marker::PhantomData,
            id,
            pool: self as *mut JRefPool,
        }
    }
    pub fn index<T: 'static>(&self, index: JRef<T>) -> &T{
        self.pool.get(&index.id).unwrap().downcast_ref::<T>().unwrap()
    }
    pub fn index_mut<T: 'static>(&mut self, index: JRef<T>) -> &mut T{
        self.pool.get_mut(&index.id).unwrap().downcast_mut::<T>().unwrap()
    }
}