use std::{any::Any, collections::{HashMap, HashSet}};
mod alternate;
mod regular;
mod safer;
use crate::regular::{JRef, JRefPool};

fn main() {
    println!("Hello, world!");
    let mut pool = JRefPool{
        pool: HashMap::new(),
    };
    let re_f = pool.insert(0, "Hello");
    let re_i = pool.insert(1, 123);
    let t = pool.index_mut(re_f);
    *t = "Test";
    println!("{}", pool.index(re_f));
}























// struct City{
//     name: String,
//     connections: HashMap<City, f64>,
//     toll: f64,
// }
// impl City{
//     fn new<T: ToString>(str: T) -> City{
//         City{
//             name: str.to_string(),
//             connections: HashMap::new(),
//             toll: 1.0,
//         }
//     }
// }
// impl PartialEq for City{
//     fn eq(&self, other: &Self) -> bool {
//         self.name == other.name
//     }
// }
// impl Eq for City{}
// impl PartialOrd for City{
//     fn partial_cmp(&self, other: &Self) -> Option<std::cmp::Ordering> {
//         self.toll.partial_cmp(&other.toll)
//     }
// }
// impl Ord for City{
//     fn cmp(&self, other: &Self) -> std::cmp::Ordering {
//         self.toll.partial_cmp(&other.toll).unwrap()
//     }
// }

// struct Airline{
//     cities: HashSet<City>,
//     hub: City,
// }

// fn cities_main(){
//     let mut cities = vec![];
//     cities.push(City::new("BDL"));
//     cities.push(City::new("BDL"));
//     cities.push(City::new("BDL"));
//     cities.push(City::new("BDL"));
// }




// fn example(){
//     let mut pool = JRefPool::new();
//     struct Tree{
//         value: i32,
//         children: Vec<JRef<Tree>>,
//     }
//     // let val = Tree{
//     //     value: 1,
//     //     // children: vec![pool.insert(id, value)],
//     // };

// }
// use std::collections::binary_heap::BinaryHeap;


// // fn dijkstra(airline: Airline){
// //     let mut to_visit = BinaryHeap::new();
// //     for city in &mut airline.cities{
// //         if city.name == airline.hub.name{
// //             city.toll = 0.0;
// //         }
// //         else{
// //             city.toll = f64::MAX;
// //         }
// //         to_visit.push(city);
// //     }
// //     while !to_visit.is_empty() {
// //         let exploring = to_visit.pop().unwrap();
// //         for (city, toll) in exploring.connections.iter_mut(){
// //             city.toll = city.toll.min(exploring.toll + *toll);
// //         }
// //     }

// // }





// // struct Hivemind{
    
// // }