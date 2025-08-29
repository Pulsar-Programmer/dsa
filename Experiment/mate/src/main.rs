use std::collections::HashMap;

pub struct Graph<T> {
    adj: Vec<Vec<usize>>,
    vertex_to_index: HashMap<T, usize>,
    index_to_vertex: Vec<T>,
    next_index: usize,
}

impl<T>
    Graph<T>
where
    T: Eq + std::hash::Hash + Clone + std::fmt::Debug,
{
    pub fn new() -> Self {
        Graph {
            adj: Vec::new(),
            vertex_to_index: HashMap::new(),
            index_to_vertex: Vec::new(),
            next_index: 0,
        }
    }

    pub fn add_vertex(&mut self, vertex: T) -> usize {
        *self.vertex_to_index.entry(vertex.clone()).or_insert_with(|| {
            let index = self.next_index;
            self.next_index += 1;
            self.adj.push(Vec::new());
            self.index_to_vertex.push(vertex);
            index
        })
    }

    pub fn add_edge(&mut self, u_val: T, v_val: T) {
        let u_idx = self.add_vertex(u_val);
        let v_idx = self.add_vertex(v_val);
        self.adj[u_idx].push(v_idx);
        self.adj[v_idx].push(u_idx);
    }

    pub fn bron_kerbosch(&self) -> Vec<Vec<T>> {
        let mut cliques_indices = Vec::new();
        let mut r = Vec::new();
        let mut p: Vec<usize> = (0..self.next_index).collect();
        let mut x = Vec::new();

        self._bron_kerbosch_recursive(&mut cliques_indices, &mut r, &mut p, &mut x);

        cliques_indices
            .into_iter()
            .map(|clique_indices| {
                clique_indices
                    .into_iter()
                    .map(|idx| self.index_to_vertex[idx].clone())
                    .collect()
            })
            .collect()
    }

    fn _bron_kerbosch_recursive(
        &self,
        cliques: &mut Vec<Vec<usize>>,
        r: &mut Vec<usize>,
        p: &mut Vec<usize>,
        x: &mut Vec<usize>,
    ) {
        if p.is_empty() && x.is_empty() {
            cliques.push(r.clone());
            return;
        }

        let p_copy = p.clone();
        for &v in &p_copy {
            let mut r_new = r.clone();
            r_new.push(v);

            let neighbors_v: Vec<usize> = self.adj[v].iter().cloned().collect();

            let mut p_intersect_n = p
                .iter()
                .filter(|&node| neighbors_v.contains(node))
                .cloned()
                .collect();
            let mut x_intersect_n = x
                .iter()
                .filter(|&node| neighbors_v.contains(node))
                .cloned()
                .collect();

            self._bron_kerbosch_recursive(cliques, &mut r_new, &mut p_intersect_n, &mut x_intersect_n);

            p.retain(|&node| node != v);
            x.push(v);
        }
    }
}

fn main() {
    let mut graph = Graph::new();
    graph.add_edge("A", "B");
    graph.add_edge("A", "C");
    graph.add_edge("B", "C");
    graph.add_edge("B", "D");
    graph.add_edge("C", "D");
    graph.add_edge("D", "E");

    let cliques = graph.bron_kerbosch();
    println!("Maximal Cliques in Rust: {:?}", cliques);
}
