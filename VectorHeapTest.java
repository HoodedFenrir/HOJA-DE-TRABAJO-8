// VectorHeapTest.java
import org.junit.Test;
import static org.junit.Assert.*;

public class VectorHeapTest {

    @Test
    public void testAddAndRemove() {
        VectorHeap<Paciente> heap = new VectorHeap<>();
        heap.add(new Paciente("Juan Perez", "fractura de pierna", 'C'));
        heap.add(new Paciente("Maria Ramirez", "apendicitis", 'A'));

        Paciente primero = heap.remove();
        assertEquals('A', primero.getCodigoEmergencia());

        Paciente segundo = heap.remove();
        assertEquals('C', segundo.getCodigoEmergencia());
    }
}
