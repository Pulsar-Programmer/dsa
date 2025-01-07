use std::{collections::HashMap, os::windows::io::OwnedSocket};

fn main() {
    let mut map = HashMap::new();
    println!("{}", dice_count_memo(3, 10, &mut map));
    println!("{map:?}")
}



// fn dice_count_memo_head(){

// }


fn dice_count_memo(dice: i32, target: i32, memo: &mut HashMap<(i32, i32), i32>) -> i32 {
    if dice == 0 && target == 0 {
        return 1;
    }
    if dice <= 0 || target <= 0 {
        return 0;
    }
    if let Some(&val) = memo.get(&(dice, target)) {
        return val;
    }
    let mut sum = 0;
    for i in 0..6{
        let i = i + 1;
        sum += dice_count_memo(dice-1, target - i, memo);
    }
    memo.insert((dice, target), sum);
    sum
}




fn dice_count_table(dice: i32, target: i32, table: &mut HashMap<(i32, i32), i32>) -> i32 {
    if dice == 0 && target == 0 {
        return 1;
    }
    if dice <= 0 || target <= 0 {
        return 0;
    }
    if let Some(&val) = table.get(&(dice, target)) {
        return val;
    }
    let mut sum = 0;
    for i in 0..6{
        let i = i + 1;
        sum += dice_count_table(dice-1, target - i, table);
    }
    table.insert((dice, target), sum);
    sum
}