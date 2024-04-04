import java.util.Vector;

/**
 * Una implementación de la cola de prioridad mínima utilizando un heap binario.
 * Este heap se basa en un vector y permite insertar y retirar elementos
 * manteniendo el orden de prioridad, donde el elemento más pequeño (según su orden natural)
 * está siempre en la raíz.
 *
 * @param <E> el tipo de elementos que la cola debe aceptar, este tipo debe implementar {@link Comparable}.
 */
public class VectorHeap<E extends Comparable<E>> {
    private Vector<E> heap; // Vector para almacenar los elementos del heap

    /**
     * Construye un nuevo VectorHeap vacío.
     */
    public VectorHeap() {
        heap = new Vector<E>();
    }

    /**
     * Añade un nuevo elemento al heap y lo ordena para mantener las propiedades del heap.
     *
     * @param item el elemento a añadir al heap.
     */
    public void add(E item) {
        heap.add(item); // Añade al final
        percolateUp(heap.size() - 1); // Reordena desde la última posición hacia arriba
    }

    /**
     * Remueve y devuelve el elemento más pequeño del heap y luego reordena el heap.
     *
     * @return el elemento más pequeño del heap.
     * @throws IllegalStateException si el heap está vacío.
     */
    public E remove() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("El heap está vacío");
        }
        E minVal = heap.get(0); // El mínimo está en la raíz
        E lastItem = heap.remove(heap.size() - 1); // Elimina el último elemento
        if (!heap.isEmpty()) {
            heap.set(0, lastItem); // Mueve el último elemento a la raíz
            percolateDown(0); // Reordena hacia abajo desde la raíz
        }
        return minVal;
    }

    /**
     * Obtiene el número de elementos en el heap.
     *
     * @return el número de elementos en el heap.
     */
    public int size() {
        return heap.size();
    }

    /**
     * Verifica si el heap está vacío.
     *
     * @return true si el heap está vacío, false en caso contrario.
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    // Métodos privados para reordenar el heap
    private void percolateUp(int nodeIndex) {
        int parentIndex;
        E tmp;
        if (nodeIndex != 0) {
            parentIndex = getParentIndex(nodeIndex);
            if (heap.get(parentIndex).compareTo(heap.get(nodeIndex)) > 0) {
                tmp = heap.get(parentIndex);
                heap.set(parentIndex, heap.get(nodeIndex));
                heap.set(nodeIndex, tmp);
                percolateUp(parentIndex);
            }
        }
    }

    private void percolateDown(int nodeIndex) {
        int childIndex, leftChildIndex, rightChildIndex;
        E tmp;
        leftChildIndex = getLeftChildIndex(nodeIndex);
        rightChildIndex = getRightChildIndex(nodeIndex);
        if (rightChildIndex >= heap.size()) {
            if (leftChildIndex >= heap.size())
                return;
            else
                childIndex = leftChildIndex;
        } else {
            childIndex = (heap.get(leftChildIndex).compareTo(heap.get(rightChildIndex)) < 0) ?
                leftChildIndex : rightChildIndex;
        }
        if (heap.get(nodeIndex).compareTo(heap.get(childIndex)) > 0) {
            tmp = heap.get(nodeIndex);
            heap.set(nodeIndex, heap.get(childIndex));
            heap.set(childIndex, tmp);
            percolateDown(childIndex);
        }
    }

    private int getParentIndex(int nodeIndex) {
        return (nodeIndex - 1) / 2;
    }

    private int getLeftChildIndex(int nodeIndex) {
        return 2 * nodeIndex + 1;
    }

    private int getRightChildIndex(int nodeIndex) {
        return 2 * nodeIndex + 2;
    }
}
