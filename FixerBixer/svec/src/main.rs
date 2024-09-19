use std::{collections::HashMap, rc::Rc};

fn main() {
    println!("Hello, world!");
}

#[test]
fn stack_test(){
    assert!(parse_expression("{[544{[12234+hhhhh55]}]235}(){}[]".into()));
    assert!(!parse_expression("{[2345354(]545})".into()));
    assert!(parse_expression("()3[]{544}".into()));
    assert!(parse_expression("5|12|34243".into()));
    assert!(!parse_expression("5|12|3424|3".into()));
    // assert!(parse_expression("".into()));
    // assert!(parse_expression("".into()));
}



enum ParseResult{
    Panic{
        // at: usize,
    },
    Success,
}

fn parse_expression(s: String) -> bool{
    let open_brackets = "{[(|";
    let closed_brackets = "}])|";
    let filtered: String = s.chars().into_iter().filter(|c|open_brackets.contains(*c) || closed_brackets.contains(*c)).collect();
    
    let mut stack = Stack::<char>::new();

    for c in filtered.chars(){
        if open_brackets.contains(c){
            stack.push(c);
        } else if closed_brackets.contains(c){
            if let Some(s) = stack.peek(){
                if c == {
                    match s {
                        '{' => '}',
                        '[' => ']',
                        '(' => ')',
                        &v => v,
                    }
                } {
                    stack.pop();
                }
                else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    true
}








struct Stack<T>{
    inner: Vec<T>,
    // cap: usize,
}
impl<T> Stack<T>{
    fn new() -> Self{
        Self { inner: Vec::new() } //, cap: 1
    }
    fn peek(&self) -> Option<&T>{
        self.inner.last()
    }
    fn pop(&mut self) -> Option<T>{
        self.inner.pop()
    }
    fn push(&mut self, val:T){
        self.inner.push(val)
    }
    fn is_empty(&self) -> bool{
        self.len() == 0
    }
    fn len(&self) -> usize{
        self.inner.len()
    }
}
impl<T> std::ops::Deref for Stack<T>{
    type Target = Vec<T>;

    fn deref(&self) -> &Self::Target {
        &self.inner
    }
}

// struct Stack{

// }



// use std::ptr;

// // Node struct representing an element in our stack
// struct Node<T> {
//     value: T,
//     next: *mut Node<T>,
// }

// // Stack struct containing the head of our linked list
// pub struct LinkedStack<T> {
//     head: *mut Node<T>,
// }

// impl<T> LinkedStack<T> {
//     // Create a new empty stack
//     pub fn new() -> Self {
//         Stack { head: ptr::null_mut() }
//     }

//     // Push a new element onto the stack
//     pub unsafe fn push(&self, value: T) {
//         let node = Box::into_raw(Box::new(Node {
//             value,
//             next: self.head,
//         }));
//         *self.head = node as *mut _;
//     }

//     // Pop the top element off the stack
//     pub unsafe fn pop(&self) -> Option<T> {
//         if self.is_empty() {
//             None
//         } else {
//             let node = *self.head;
//             *self.head = (*self.head).next;
//             Box::from_raw(node)
//         }
//     }

//     // Peek at the top element without removing it
//     pub unsafe fn peek(&self) -> Option<&T> {
//         if self.is_empty() {
//             None
//         } else {
//             Some(&(*self.head).value)
//         }
//     }

//     // Check if the stack is empty
//     pub unsafe fn is_empty(&self) -> bool {
//         self.head.is_null()
//     }

//     // Clean up the stack and free all allocated memory
//     pub unsafe fn cleanup(&self) {
//         while !self.is_empty() {
//             let node = self.pop().unwrap();
//             Box::from_raw(node);
//         }
//     }
// }

// // Implement Send trait for thread-safe usage
// unsafe impl<T: Send> Send for Stack<T> {}

// // Implement Sync trait for shared access between threads
// unsafe impl<T: Send + Sync> Sync for Stack<T> {}


const OPERANDS: [char; 4] = ['+','-','/','*'];

fn infix_to_postfix(mut s: String){
    let mut expr = String::new();
    let mut stack = Stack::new();
    for i in s.chars(){
        if i == '('{
            stack.push(i.to_string());
        } else if todo!("is_operand") {
            expr.push(i);
        } else if i == ')'{
            loop{
                let Some(val) = stack.pop() else { break };
                if &val == "(" {
                    break
                }
                expr.push_str(&val);
            }
        } else if todo!("is_operator"){
            while let Some(val) = stack.pop() {
                if todo!("not_operator") && todo!("lower_precedence") {
                    stack.push(i.to_string());
                    break
                }
                expr.push_str(&val);
            }
        }
    }
}


#[derive(Eq, PartialEq, Debug)]
struct WeirdQueue<T>{
    inner: HashMap<isize, T>,
    min: isize, // used to save time
    max: isize, // used to save time
}
impl<T> WeirdQueue<T>{
    fn new() -> Self{
        Self { inner: HashMap::new(), min: 1, max: 0 }
    }
    fn add(&mut self, elem: T){
        self.inner.insert(self.min - 1, elem);
        self.min -= 1;
    }
    fn remove(&mut self) -> T{
        let val = self.inner.remove(&self.max).unwrap();
        self.max -= 1;
        val
    }
    fn peek(&self) -> &T{
        &self.inner[&self.max]
    }
    fn size(&self) -> isize{
        self.max - self.min
    }
    fn is_empty(&self) -> bool{
        self.size() == 0
    }
}
impl<T> Default for WeirdQueue<T> {
    fn default() -> Self {
        Self::new()
    }
}

#[test]
fn test_queue(){
    let n = 5;
    let mut queue = WeirdQueue::new();
    queue.add("1".to_string());
    for i in 0..n{
        let output = queue.remove();
        println!("{output}");
        queue.add(output.to_string() + "0");
        queue.add(output.to_string() + "1");
    }
}

#[test]
fn mirror(){
    let mut q = WeirdQueue::new();
    q.add(5);
    q.add(56);
    q.add(31);
    q.add(3);
    q.add(4);

    let mut s = Stack::new();
    let size = q.size();
    for _ in 0..size{
        s.push(q.remove());
    }
    for _ in 0..size{
        q.add(s.pop().unwrap_or_default());
    }

    println!("{:?}", q.inner);

    let mut q2 = WeirdQueue::new();
    q2.add(4);
    q2.add(3);
    q2.add(31);
    q2.add(56);
    q2.add(5);

    // assert_eq!(q, q2)
}







struct LinkedList<T> {
    head: T,
    rest: Rc<LinkedList<T>>,
}
impl<T> LinkedList<T>{
    fn insert(&mut self, index: usize){
        let mut rc_ref = self.rest.clone();
        for _ in 0..=index{
            
        }
    }
}