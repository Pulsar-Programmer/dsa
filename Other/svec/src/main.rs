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
            
        }
    }
}