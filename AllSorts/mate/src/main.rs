fn main() {
    let unsorted = vec![5, 235, 7, 2, 46, 3, 7, 3];
    println!("{:?}", outplace_recursive_vec_merge_sort(unsorted));
}


fn outplace_recursive_vec_merge_sort<T: Ord>(mut v1: Vec<T>) -> Vec<T>{

    if v1.len() == 1{
        return v1;
    }
    
    let v2 = v1.split_off(v1.len()/2);
        
    let mut res_v1 = outplace_recursive_vec_merge_sort(v1);
    let mut res_v2 = outplace_recursive_vec_merge_sort(v2);

    let mut new = Vec::new();

    while !res_v1.is_empty() || !res_v2.is_empty(){
        let a = match res_v1.first(){
            Some(a) => a,
            None => {new.push(res_v2.remove(0)); continue},
        };
        let b = match res_v2.first(){
            Some(b) => b,
            None => {new.push(res_v1.remove(0)); continue},
        };
        if a <= b {
            new.push(res_v1.remove(0));
        } else{
            new.push(res_v2.remove(0));
        }
    }

    new






}