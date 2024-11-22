fn main() {
    println!("Hello, world!");
}


//this can probably be implemented from the ground up and not on the vec but it is ok
//it is meant to be used in circumstances where the extra memory is not needed
struct StrictCappedVec<T>{
    inner: Vec<T>
}
impl<T> StrictCappedVec<T>{
    fn push(&mut self, element: T){
        let mut new = Vec::with_capacity(self.inner.capacity() + 1);
        std::mem::swap(&mut self.inner, &mut new);
        todo!()
    }
}

type Natural = GrowableNumber;
struct GrowableNumber{
    number: Vec<u128>,
}

type Integer = GrowableInteger;
struct GrowableInteger{
    sign: bool,
    natural: Natural,
}







struct Hello<T> {
    ptr: *mut T,
    len: usize,
    capacity: usize,
}

impl<T> Hello<T> {
    // Create a new empty Hello with initial capacity
    pub fn new() -> Self {
        Self {
            ptr: std::ptr::null_mut(),
            len: 0,
            capacity: 0,
        }
    }

    // Create a new Hello with specified capacity
    pub fn with_capacity(capacity: usize) -> Self {
        let layout = std::alloc::Layout::array::<T>(capacity).unwrap();
        let ptr = unsafe { std::alloc::alloc(layout) as *mut T };
        
        Self {
            ptr,
            len: 0,
            capacity,
        }
    }

    // Push a value to the vector
    pub fn push(&mut self, value: T) {
        if self.len == self.capacity {
            let new_capacity = if self.capacity == 0 { 1 } else { self.capacity * 2 };
            self.grow(new_capacity);
        }
        
        unsafe {
            std::ptr::write(self.ptr.add(self.len), value);
        }
        self.len += 1;
    }

    // Grow the capacity of the vector
    fn grow(&mut self, new_capacity: usize) {
        let new_layout = std::alloc::Layout::array::<T>(new_capacity).unwrap();
        let new_ptr = if self.capacity == 0 {
            unsafe { std::alloc::alloc(new_layout) as *mut T }
        } else {
            let old_layout = std::alloc::Layout::array::<T>(self.capacity).unwrap();
            unsafe { 
                let ptr = std::alloc::realloc(
                    self.ptr as *mut u8,
                    old_layout,
                    new_capacity * std::mem::size_of::<T>()
                ) as *mut T;
                ptr
    }
        };

        self.ptr = new_ptr;
        self.capacity = new_capacity;
}
}

// Implement Drop to properly deallocate memory
impl<T> Drop for Hello<T> {
    fn drop(&mut self) {
        if self.capacity > 0 {
            unsafe {
                let layout = std::alloc::Layout::array::<T>(self.capacity).unwrap();
                std::alloc::dealloc(self.ptr as *mut u8, layout);
            }
        }
    }
}



#[test]
fn test(){
    let v = vec![75, 437,2 ,4, 3, 67, 5, 5,8, 997];
    let v = quick_sort(v);
    println!("{v:?}");
}


fn quick_sort<T: Ord>(mut v: Vec<T>) -> Vec<T>{
    if v.len() == 1 { return v }
    let p = v.remove(0);
    let mut v1 = Vec::new();
    let mut v2 = Vec::new();
    for i in v {
        if i < p {
            v1.push(i);
        } else {
            v2.push(i);
        }
    }
    v1.push(p);
    let mut v = quick_sort(v1);
    v.extend(v2);
    v
}
