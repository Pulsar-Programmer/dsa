fn main() {
    println!("Hello, world!");
}






// fn newtons<const N: usize>(coefficients: [f64; N], trials: usize) -> Option<f64>{
//     if trails >= 1000{
//         return None; 
//     }
//     Some('a: {



//         return 2. 'a;
//     })
// }

// fn derivative(mut coefficients: Vec<f64>) -> Vec<f64> {
//     let len = coefficients.len();
//     coefficients.pop();
//     for i in coefficients.iter_mut(){
//         *i *= len as f64 - 1.;
//     }
//     coefficients
// }



// fn evaluate<const N: usize>(coefficients: [f64; N], value: f64) -> f64 {
//     let mut v = 0f64;
//     for i in 0..N{
//         v += coefficients[i].powi((N - i) as i32);
//     }
//     v
// }





struct Item{
    weight: f64,
    value: f64,
}


fn knapsack(capacity: usize, refs: &Vec<Item>, items: Vec<usize>) -> Option<Vec<usize>> {
    todo!()
}

// fn largest(refs: &Vec<Item>, items_vecs: Vec<Option<Vec<usize>>>) -> Option<Vec<usize>>{
//     items_vecs.into_iter().map(|items|{
//         match items{
//             Some(items) => Some(items.iter().map(|i|refs[*i].value).sum() as usize),
//             None => None,
//         }
//     }).reduce(|a, b|{
//         if let Some(a) = a{
//             if let Some(b) = b{
//                 Some(a.max(b))
//             } else {
//                 Some(a)
//             }
//         } else {
//             b
//         }
//     })
// }