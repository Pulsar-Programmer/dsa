use std::{collections::LinkedList, mem::{ManuallyDrop, MaybeUninit}, ops::Deref, rc::{Rc, Weak}};

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


enum ConsNilList<T>{
    Cons(Box<ConsNilList<T>>),
    Nil(T)
}

// union Test{
//     one: f64,
//     strog: ManuallyDrop<String>,
// }

// #[test]
// fn test(){
//     let mut test = Test{strog: ManuallyDrop::new("Hello".to_string()) };
//     unsafe { println!("{}", test.one) };
//     test.one = 543636.123;
//     unsafe { println!("{}", *test.strog) };
//     unsafe { ManuallyDrop::drop(&mut test.strog); }
// }


fn monkey() -> i32{
    let t = 5;
    let j = vec![2, 3, 4, 5, 6];
    for i in 0..j.len(){
        println!("{i}");
    };
    let a = |five: i32|{
        five
    };
    use std::ops::{Deref, DerefMut, Div, DivAssign, MulAssign, RemAssign};
    match t {
        6 => 3,
        _ => 4,
    }
}



type Next<T> = Option<Rc<T>>;

struct Node<T>{
    data: T,
    next: Next<Node<T>>,
}


// struct RCLinkedList<T>{
//     head: Next<Node<T>>,
// }
// impl<T> RCLinkedList<T>{
//     ///Returns a reference to the `nth` node in the linked list. Returns the last element if it is `n` is too large,
//     fn nth(&self, n: usize) -> Weak<RCLinkedList<T>>{
//         let mut count = 0;
//         let mut runner = self.head;

//         loop{
//             if count == n {
//                 return runner;
//             }
//             let Some(val) = runner.next else { break };
            
//             count += 1;
//         }
//         runner
//     }
// }










struct SinglyLinkedList<'a, T>{
    head: Option<&'a SinglyLinkedListNode<'a, T>>
}
impl<T> SinglyLinkedList<'_, T>{
    fn new() -> Self{
        Self { head: None }
    }
    
    ///
    #[doc(alias("size", "length"))]
    fn len(&self) -> usize{
        match self.head{
            Some(v) => v.len(),
            None => 0,
        }
    }
    fn is_empty(&self) -> bool{
        self.head.is_none()
    }


    // fn push(&mut self, val: T){

    // }

    
}





struct SinglyLinkedListNode<'a, T>{
    data: T,
    next: Option<&'a SinglyLinkedListNode<'a, T>>,
}
impl<T> SinglyLinkedListNode<'_, T>{
    fn with_data(t: T) -> Self{
        Self { data: t, next: None }
    }
    fn len(&self) -> usize{
        let mut counter = 0;
        let mut reference = self;
        while let Some(v) = reference.next{
            reference = v;
            counter += 1;
        }
        counter
    }
}
// impl<'a, T> Iterator for SinglyLinkedListNode<'a, T>{
//     type Item = &'a mut T;

//     fn next(&mut self) -> Option<Self::Item> {
//         let mut reference = self;
//         while let Some(v) = reference.next{
//             reference = v;
//         }
//         reference
//     }
// }



#[derive(Default)]
struct StackLinkedList<T>{
    inner: LinkedList<T>,
}
impl<T> StackLinkedList<T>{
    
}