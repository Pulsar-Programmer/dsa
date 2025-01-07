fn main() {
    println!("Hello, world!");
}






fn newtons<const N: usize>(coefficients: [f64; N], trials: usize) -> Option<f64>{
    if trails >= 1000{
        return None; 
    }
    Some('a: {



        return 2. 'a;
    })
}

fn derivative(mut coefficients: Vec<f64>) -> Vec<f64> {
    let len = coefficients.len();
    coefficients.pop();
    for i in coefficients.iter_mut(){
        *i *= len as f64 - 1.;
    }
    coefficients
}



fn evaluate<const N: usize>(coefficients: [f64; N], value: f64) -> f64 {
    let mut v = 0f64;
    for i in 0..N{
        v += coefficients[i].powi((N - i) as i32);
    }
    v
}