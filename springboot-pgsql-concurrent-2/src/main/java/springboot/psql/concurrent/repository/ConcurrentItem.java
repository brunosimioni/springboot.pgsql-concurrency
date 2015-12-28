package springboot.psql.concurrent.repository;


public class ConcurrentItem {

	public ConcurrentItem() {
	};

	private int id;

	private long counter2;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getCounter2() {
		return counter2;
	}

	public void setCounter2(long counter2) {
		this.counter2 = counter2;
	}
}