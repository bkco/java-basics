package co.bk.javabasics.main.queues;

interface Queue<T> {
    Queue<T> enqueue(T ele);
    T dequeue();
}