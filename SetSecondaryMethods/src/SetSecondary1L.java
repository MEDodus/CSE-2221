import components.set.Set;
import components.set.Set1L;

/**
 * Layered implementations of secondary methods {@code add} and {@code remove}
 * for {@code Set}.
 *
 * @param <T>
 *            type of {@code Set} elements
 */
public final class SetSecondary1L<T> extends Set1L<T> {

    /**
     * No-argument constructor.
     */
    public SetSecondary1L() {
        super();
    }

    /*
     * Remove elements of s from this.
     */
    @Override
    public Set<T> remove(Set<T> s) {
        assert s != null : "Violation of: s is not null";
        assert s != this : "Violation of: s is not this";

        Set<T> temp = s.newInstance();
        Set<T> allRemoved = s.newInstance();
        temp.transferFrom(s);
        while (temp.size() > 0) {
            T x = temp.removeAny();
            if (this.contains(x)) {
                allRemoved.add(this.remove(x));
            }
            s.add(x);
        }
        return allRemoved;

    }

    @Override
    public void add(Set<T> s) {
        assert s != null : "Violation of: s is not null";
        assert s != this : "Violation of: s is not this";

        Set<T> temp = s.newInstance();
        temp.transferFrom(s);
        while (temp.size() > 0) {
            T x = temp.removeAny();
            if (this.contains(x)) {
                s.add(x);
            } else {
                this.add(x);
            }
        }

    }

    /**
     * Reports whether {@code this} is a subset of {@code s}. (A is a subset of
     * B exactly when every element of A is also an element of B.)
     *
     * @param s
     *            the second set
     * @return whether {@code this} is a subset of {@code s}
     * @ensures isSubset = (this is a subset of s)
     */
    @Override
    public boolean isSubset(Set<T> s) {
        assert s != null : "Violation of: s is not null";
        assert s != this : "Violation of: s is not this";

        Set<T> temp = this.newInstance();
        temp.transferFrom(this);
        int sizeComparison = temp.size();
        int cnt = 0;
        boolean isSubset = false;
        while (temp.size() > 0) {
            T x = temp.removeAny();
            if (s.contains(x)) {
                cnt++;
            }
            this.add(x);
        }
        if (cnt == sizeComparison) {
            isSubset = true;
        }
        return isSubset;
    }

}
