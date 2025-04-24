

fn main() {
    
}



fn handle() -> std::io::Stdin{
    std::io::stdin()
}

fn next_line(handle: &std::io::Stdin) -> String{
    let mut input = String::new();
    handle.read_line(&mut input).unwrap();
    input.trim().to_string()
}
