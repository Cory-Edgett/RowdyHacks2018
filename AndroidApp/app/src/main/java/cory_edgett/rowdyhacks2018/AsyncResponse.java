package cory_edgett.rowdyhacks2018;

public interface AsyncResponse<T> {
    void processFinish(T value);
}
