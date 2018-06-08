class Pair<L, R> {
    private L key;
    private R value;

    Pair(L key, R value) {
        this.key = key;
        this.value = value;
    }

    public L getKey() {
        return key;
    }

    public void setKey(L key) {
        this.key = key;
    }

    public R getValue() {
        return value;
    }

    public void setValue(R value) {
        this.value = value;
    }
}
