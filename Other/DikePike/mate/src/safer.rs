





pub struct JRef<T>{
    pub phantom: std::marker::PhantomData<T>,
    pub id: usize,
    pub pool: *mut JRefPool, //unsafe
}
impl<T> Clone for JRef<T> {
    fn clone(&self) -> Self {
        Self { phantom: self.phantom.clone(), id: self.id.clone(), pool: self.pool.clone() }
    }
}
impl<T> Copy for JRef<T> {}
impl<T: 'static> JRef<T>{
    unsafe fn index(&self) -> &T{
        // self.pool.get(&index.id).unwrap().downcast_ref::<T>().unwrap()
        self.pool.as_ref().unwrap().pool.get(&self.id).unwrap().downcast_ref::<T>().unwrap()
    }
    unsafe fn index_mut(&mut self) -> &mut T{
        self.pool.as_mut().unwrap().pool.get_mut(&self.id).unwrap().downcast_mut::<T>().unwrap()
    }
}

use std::{any::Any, collections::HashMap};


pub struct JRefPool{
    pool: HashMap<usize, Box<dyn Any>>,
}
impl JRefPool{
    fn new() -> JRefPool{
        JRefPool{
            pool: HashMap::new(),
        }
    }
    fn insert_alternate<T: 'static>(&mut self, id: usize, value: T) -> JRef<T>{
        self.pool.insert(id, Box::new(value));
        JRef{
            phantom: std::marker::PhantomData,
            id,
            pool: self as *mut JRefPool,
        }
    }
    fn index<T: 'static>(&self, index: JRef<T>) -> &T{
        self.pool.get(&index.id).unwrap().downcast_ref::<T>().unwrap()
    }
    fn index_mut<T: 'static>(&mut self, index: JRef<T>) -> &mut T{
        self.pool.get_mut(&index.id).unwrap().downcast_mut::<T>().unwrap()
    }
}
impl Drop for JRefPool {
    fn drop(&mut self) {
        // println!("Dropping JRef with id: {}", self.id);
        // self.pool.as_mut().unwrap().pool.remove(&self.id);
    }
}


