// use petgrap
use petgraph::{graph::UnGraph, Graph};





fn main() {
    let mut graph = UnGraph::<char, ()>::new_undirected();
    let n = 10i32;
    for i in 0..n{
        graph.add_node((i as u8 + b'A') as char);
    }
    for _ in 0..n.pow(2)/2{
        let a = rand::random_range(0..n) as u32;
        let b = rand::random_range(0..n) as u32;
        if a != b {
            graph.add_edge(a.into(), b.into(), ());
        }
    }

    println!("{:?}", graph);      

    special_fn(&graph);

}



fn special_fn(graph: &UnGraph<char, ()>) -> Vec<petgraph::prelude::NodeIndex> {
    println!("This is a special function."); //thanks copilot !! lMFAOOOOOo
    // let mut potential_cliques = Vec::<Vec<_>>::new();

    // for i in graph.node_indices(){
        // println!("Node {}: {}", i.index(), graph[i]);
        let mut potential_clique = Vec::<_>::new();
        for j in graph.node_indices(){
            if graph.find_edge(0.into(), j).is_some(){
                println!("  Connected to {}: {}", j.index(), graph[j]);
            }
            potential_clique.push(j);
        }
        // potential_cliques.push(potential_clique);
    // }
    let mut rm_vec = Vec::<_>::new();
    for i in potential_clique.iter() {
        println!("Potential clique member: {}", i.index());
        for j in potential_clique.iter() {
            if i == j{
                continue;
            }
            if graph.find_edge(*i, *j).is_none() {
                println!("  Not connected to {}: {}", j.index(), graph[*j]);
                rm_vec.push(*i);
            }
        }
    }
    potential_clique.retain(|&x| !rm_vec.contains(&x));
    println!("Potential clique members after filtering:");
    for i in potential_clique.iter() {
        println!("{}", i.index());
    }
    potential_clique
}
