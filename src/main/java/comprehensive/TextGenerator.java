package comprehensive;


public class TextGenerator {
	public static void main(String[] args) {
		if (args.length < 3) {
			System.out.println("Please provide at least 3 arguments");
		}
		String path = args[0];
		String seed = args[1];
		String K = args[2];

		String opts = null;

		if (args.length == 4) {
			opts = args[3];
		}
		TextGeneratorProcess processor = new TextGeneratorProcess(path, seed, Integer.parseInt(K), opts);
		processor.run();

	}

}
