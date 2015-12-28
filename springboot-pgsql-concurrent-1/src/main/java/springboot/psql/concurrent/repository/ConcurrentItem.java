package springboot.psql.concurrent.repository;


public class ConcurrentItem {

	public ConcurrentItem() {
	};

	private int id;

	private long counter1;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getCounter1() {
		return counter1;
	}

	public void setCounter1(long counter1) {
		this.counter1 = counter1;
	}
}