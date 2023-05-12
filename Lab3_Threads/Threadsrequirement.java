/***********************************Requirement description**********************************************/
// •	There is a bookstore that has different branches, each of them sell a number of books, these books are supplied by the Supplier. 
// •	A bookstore shouldn’t sell a book when the number of books is 0, it should block and notify the Supplier to provide more
// •	A supplier shouldn’t provide a book when the max count of books is reached, when it provides a book, it should tell stores that there are more books available.
// •	Modify the requirement_student.java code to reflect this behavior.
// •	All threads should execute in parallel, you cannot allow a thread to stop another thread (should guarantee progress)
// •	Follow the 8 TODOs in the Code
/***********************************************************************************************/

class Threadsrequirement {
	public static void main(String[] args) throws InterruptedException {

		BookStock b = new BookStock(10);

		// TODO-1: Create 4 threads,
		// 1 for Supplier
		Thread supplier = new Supplier(b);

		// 3 for StoreBranches and Name them as the following: Giza branch, Cairo
		// branch, Daqahley branch
		Thread Giza = new StoreBranch(b);
		Thread Cairo = new StoreBranch(b);
		Thread Daqahleya = new StoreBranch(b);

		// setting name for each thread
		supplier.setName("Supplier");
		Giza.setName("Giza Branch");
		Cairo.setName("Cairo Branch");
		Daqahleya.setName("Daqahleya Branch");

		// TODO-2: Run the 4 threads
		supplier.start();
		Giza.start();
		Cairo.start();
		Daqahleya.start();
	}
}

class BookStock {
	private int book_count;
	public int max;

	public BookStock(int max) {
		this.max = max;
	}

	public void produce() {
		book_count++;
	}

	public void consume() {
		book_count--;
	}

	public int getCount() {
		return book_count;
	}
}

//TODO-3: should it implement or extend anything?
class Supplier extends Thread {
	private BookStock b;

	public Supplier(BookStock b) {
		this.b = b;
	}

//TODO-4:is a function missing ?
	public void run() {
		doWork();
	}

	public void doWork() {
		while (true) {
			synchronized (b) {
				try {
					// TODO-5: how to make it stop producing when it reaches max,
					// without adding extra sleeps or busy waiting ?
					while (b.getCount() == b.max)
						b.wait(); // wait until the store has a room to store in

					b.produce();
					/*
					 * Object.wait() to suspend a thread 
					 * Object.notify() to wake a thread up
					 */
					b.notifyAll();

					System.out.println(Thread.currentThread().getName() + " provided a book, total " + b.getCount());

				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}
			}

			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName() + "is awaken");
			}

		}
	}
}

//TODO-6: should it implement or extend anything?
class StoreBranch extends Thread {
	private BookStock b;

	public StoreBranch(BookStock b) {
		this.b = b;
	}

//TODO-7: is a function missing ?
	public void run() {
		doWork();
	}

	public void doWork() {
		while (true) {
			synchronized (b) {
				try {
					// TODO-8: how to make it stop consuming when the store is empty,
					// without adding extra sleeps or busy waiting ?
					while (b.getCount() == 0)
						b.wait(); // wait while the store is empty

					b.consume();

					b.notifyAll();

					/*
					 * Object.wait() to suspend a thread 
					 * Object.notify() to wake a thread up
					 */
					System.out.println(Thread.currentThread().getName() + " sold a book");
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName() + "is awaken");
			}
		}
	}
}