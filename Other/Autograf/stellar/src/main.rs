use std::collections::{HashMap, HashSet};

fn main() {
    println!("Hello, world!");
}




mod edge_set{
    mod directed{
        struct Graph<T>{
            nodes: Vec<T>,
            edges: Vec<(usize, usize)>,
        }
    }
    mod undirected{
        struct Graph<T>{
            nodes: Vec<T>,
            edges: Vec<[usize; 2]>,
        }
    }
}
mod adjacency_set{
    mod directed{
        use std::collections::HashMap;

        struct Graph<T>{
            nodes: Vec<T>,
            edges: HashMap<usize, Vec<usize>>,
        }
    }
    mod undirected{
        use std::collections::HashMap;

        struct Graph<T>{
            nodes: Vec<T>,
            edges: HashMap<usize, Vec<usize>>,
        }
    }
}
// mod table/adjacency list/matrix{
//     mod directed{
//         struct Graph<T>{
//             nodes: Vec<T>,
//             table: Vec<Vec<usize>>,
//             // table: Vec<Vec<usize>>,
//         }
//     }
//     mod undirected{
//         struct Graph{
            
//         }
//     }
// }
// mod disjointed_set (each node is a struct and you linked list ahh it)

struct DSACottage<'a, T: Eq + std::hash::Hash>{
    graph: HashMap<T, HashSet<&'a T>>,


}
impl<'a, T: Eq + std::hash::Hash> DSACottage<'a, T>{
    pub fn new() -> Self{
        DSACottage{
            graph: HashMap::new(),
        }
    }
    pub fn add_node(&mut self, node: T){
        self.graph.insert(node, HashSet::new());
    }
    pub fn replace_node_and_edges(&mut self, node: T, edges: HashSet<&'a T>){
        let cleared_set = edges.into_iter().filter(|&v|{self.graph.contains_key(v)}).collect();
        self.graph.insert(node, cleared_set);
    }
    pub fn add_edges(&mut self, node: &'a T, edges: HashSet<&'a T>) -> Result<(), ()>{
        let sanitized_set: HashSet<&'a T> = edges.into_iter().filter(|&v|{self.graph.contains_key(v)}).collect();
        self.graph.get_mut(node).ok_or(())?.extend(sanitized_set);
        Ok(())
    }
    // pub fn remove_node(&mut self, node: &'a T){
        //can you create a borrow checker failure if you remove? weird
    // }
}

struct Node{

}
