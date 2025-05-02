use crate::regular::JRefPool;

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