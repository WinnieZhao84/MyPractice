package LeetCode.Interview.Amazon;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 在Movie network 里寻找N 个分数最高的Movie
 * 给一个movie，每个movie 有id，rating 和一个list of neighbors
 * 你从这个movie 开始，找到similar 的top k rating movie，不包括这个movie
 *
 * Exp:
 * m0 <-­-­>m1, similarity 2
 * m0 <-­-­> m2, similarity 3
 * m1 <-­-­> m3, similarity 4
 * m2 <-­-­> m5, similaity 5
 * 如果要返回和mo 最相似的movie, 那么应该返回 m5 (只有有一条路径从 m1 到 m5, 并且 5 是最大的)
 * 如果返回3个就是m2, m3, m5
 *
 * Created by WinnieZhao on 9/28/2017.
 */
public class Movies {

    public static class Movie {
        private final int movieId;
        private final float rating;
        private List<Movie> similarMovies; // Similarity is bidirectional

        public Movie(int movieId, float rating) {
            this.movieId = movieId;
            this.rating = rating;
            similarMovies = new ArrayList<>();
        }

        public int getId() {
            return movieId;
        }

        public float getRating() {
            return rating;
        }

        public void addSimilarMovie(Movie movie) {
            similarMovies.add(movie);
            movie.similarMovies.add(this);
        }

        public List<Movie> getSimilarMovies() {
            return similarMovies;
        }
    }

    public static List<Movie> getMovieRecommendations(Movie movie, int numSimilar) {
        PriorityQueue<Movie> priorityQueue = new PriorityQueue<>((m1, m2) -> (int) (m1.getRating() - m2.getRating()));

        dfsSearchMovies(movie, priorityQueue);

        while(priorityQueue.size() > numSimilar) {
            priorityQueue.poll();
        }

        return new ArrayList<>(priorityQueue);
    }

    private static void dfsSearchMovies(Movie movie, PriorityQueue<Movie> queue){
        for (Movie m : movie.getSimilarMovies()){
            if (!queue.contains(m)){

                queue.offer(m);

                dfsSearchMovies(m, queue);
            }
        }
    }

    public static void main(String args[]) {
        /**
         *              A(Rating 1.2)
         *               /   \
         *            B(2.4)  C(3.6)
         *              \     /
         *                D(4.8)
         */
        Movie a = new Movie(1, (float) 1.2);
        Movie b = new Movie(2, (float) 2.4);
        Movie c = new Movie(3, (float) 3.6);
        Movie d = new Movie(4, (float) 4.8);

        a.addSimilarMovie(b);
        a.addSimilarMovie(c);
        b.addSimilarMovie(d);
        c.addSimilarMovie(d);

        List<Movie> case1 = getMovieRecommendations(a, 2);
        System.out.println(case1.contains(c));
        System.out.println(case1.contains(d));

        List<Movie> case2 = getMovieRecommendations(a, 4);
        System.out.println(case2.contains(a));
        System.out.println(case2.contains(b));
        System.out.println(case2.contains(c));
        System.out.println(case2.contains(d));

        List<Movie> case3 = getMovieRecommendations(a, 1);
        System.out.println(case3.contains(d));
    }
}
