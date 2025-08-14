package common.domain;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIdGenerator implements IdGenerator {
    private final AtomicInteger seq;
    public AtomicIdGenerator(int seed) { this.seq = new AtomicInteger(seed); }
    @Override public int nextId() { return seq.incrementAndGet(); }
}
