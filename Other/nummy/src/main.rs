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