use std::{any::Any, collections::{HashMap, HashSet}};

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


struct JRef<T>{
    phantom: std::marker::PhantomData<T>,
    id: usize,
    // pool: *mut JRefPool,
}
impl<T> Clone for JRef<T> {
    fn clone(&self) -> Self {
        Self { phantom: self.phantom.clone(), id: self.id.clone() }
    }
}
impl<T> Copy for JRef<T> {}

struct JRefPool{
    pool: HashMap<usize, Box<dyn Any>>,
}
impl JRefPool{
    fn new() -> JRefPool{
        JRefPool{
            pool: HashMap::new(),
        }
    }
    fn insert<T: 'static>(&mut self, id: usize, value: T) -> JRef<T>{
        self.pool.insert(id, Box::new(value));
        JRef{
            phantom: std::marker::PhantomData,
            id,
            // pool: self as *mut JRefPool,
        }
    }
    fn index<T: 'static>(&self, index: JRef<T>) -> &T{
        self.pool.get(&index.id).unwrap().downcast_ref::<T>().unwrap()
    }
    fn index_mut<T: 'static>(&mut self, index: JRef<T>) -> &mut T{
        self.pool.get_mut(&index.id).unwrap().downcast_mut::<T>().unwrap()
    }
}



struct City{
    name: String,
    connections: HashMap<City, f64>,
    toll: f64,
}
impl City{
    fn new<T: ToString>(str: T) -> City{
        City{
            name: str.to_string(),
            connections: HashMap::new(),
            toll: 1.0,
        }
    }
}

struct Airline{
    cities: HashSet<City>,
    hub: City,
}

fn cities_main(){
    let mut cities = vec![];
    cities.push(City::new("BDL"));
    cities.push(City::new("BDL"));
    cities.push(City::new("BDL"));
    cities.push(City::new("BDL"));
}




fn example(){
    let mut pool = JRefPool::new();
    struct Tree{
        value: i32,
        children: Vec<JRef<Tree>>,
    }
    // let val = Tree{
    //     value: 1,
    //     // children: vec![pool.insert(id, value)],
    // };

}