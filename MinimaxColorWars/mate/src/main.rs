fn main() {
    println!("Hello, world!");
}


#[derive(Debug, Clone, Copy)]
enum Color{
    Red,
    Blue,
}
//could be modeled by Option<bool>

struct Board<const A: usize, const B: usize> {
    board: [[Option<Color>; A]; B],
    turn: Color,
}
impl std::default::Default for Board<8, 8> {
    fn default() -> Self {
        Self {
            board: [[const { None }; 8]; 8],
            turn: Color::Red,
        }
    }
}