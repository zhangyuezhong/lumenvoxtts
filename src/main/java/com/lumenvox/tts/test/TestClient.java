package com.lumenvox.tts.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.lumenvox.tts.LVTTSClient;
import com.lumenvox.tts.LVTTSClientException;
import com.lumenvox.tts.SYNTHESIS_SAMPLING_RATE;
import com.lumenvox.tts.SYNTHESIS_SOUND_FORMAT;
import com.lumenvox.tts.WaveOutputStream;

public class TestClient {

	public static void main(String[] args) {
		LVTTSClient client = new LVTTSClient();
		try {
			System.out.println("invoke LV_SRE_Startup");
			LVTTSClient.LV_SRE_Startup();

			System.out.println("invoke LV_TTS_CreateClient");
			// System.out.println("invoke setPROP_EX_TTS_SERVERS");
			client.setPROP_EX_TTS_SERVERS("10.191.37.61");

			client.LV_TTS_CreateClient();
			// client.setPROP_EX_SYNTHESIS_LANGUAGE("");
			// client.setPROP_EX_SYNTH_VOICE_GENDER("");
			// client.setPROP_EX_SYNTH_VOICE_NAME("");
			System.out.println("invoke getPROP_EX_TTS_SERVERS");
			System.out.println(client.getPROP_EX_TTS_SERVERS());

			System.out.println("is server avaialble" + client.LV_TTS_IsServerAvailable());
			System.out.println("invoke getPROP_EX_SYNTHESIS_LANGUAGE");
			System.out.println(client.getPROP_EX_SYNTHESIS_LANGUAGE());
			System.out.println("invoke getPROP_EX_SYNTH_VOICE_GENDER");
			System.out.println(client.getPROP_EX_SYNTH_VOICE_GENDER());
			System.out.println("invoke getPROP_EX_SYNTH_VOICE_NAME");
			System.out.println(client.getPROP_EX_SYNTH_VOICE_NAME());
			System.out.println("invoke setPROP_EX_SYNTH_SOUND_FORMAT");
			client.setPROP_EX_SYNTH_SOUND_FORMAT(SYNTHESIS_SOUND_FORMAT.SFMT_ULAW);
			System.out.println("invoke setPROP_EX_SYNTHESIS_SAMPLING_RATE");
			client.setPROP_EX_SYNTHESIS_SAMPLING_RATE(SYNTHESIS_SAMPLING_RATE._8000Hz);
			System.out.println("invoke getPROP_EX_SYNTH_SOUND_FORMAT");
			System.out.println(client.getPROP_EX_SYNTH_SOUND_FORMAT());
			System.out.println("invoke getPROP_EX_SYNTHESIS_SAMPLING_RATE");
			System.out.println(client.getPROP_EX_SYNTHESIS_SAMPLING_RATE());

			System.out.println("invoke LV_TTS_Synthesize");

			// String path =
			// "/usr/share/lumenvox/client/data/SimpleTTSClient.ssml";

			// String ssml = readFile(path, Charset.forName("UTF-8"));

			String ssml = "Microsoft tries to move reusability from the source code realm into the binary realm";
			long start = System.currentTimeMillis();
			client.LV_TTS_Synthesize(ssml);
			int length = client.LV_TTS_GetSynthesizedAudioBufferLength();
			byte[] byteArray = client.LV_TTS_GetSynthesizedAudioBuffer(length);
			long end = System.currentTimeMillis();
			System.out.println("it takes =" + (end - start));
			File file = new File("hello.wav");

			// WaveWriter writer = new WaveWriter(file,
			// WaveWriter.SAMPLING_RATE_8000Hz, WaveWriter.CHANNEL_MONO,
			// WaveWriter.AUDIO_FORMAT_ULAW);
			// writer.createWaveFile();
			// writer.write(byteArray, 0, byteArray.length);
			// writer.closeWaveFile();

			WaveOutputStream out = new WaveOutputStream(new FileOutputStream(file),
					WaveOutputStream.SAMPLING_RATE_8000Hz, WaveOutputStream.CHANNEL_MONO,
					WaveOutputStream.AUDIO_FORMAT_ULAW);
			out.write(byteArray, 0, byteArray.length);
			out.close();

		} catch (Exception e) {
			e.printStackTrace(System.out);
			if (e instanceof LVTTSClientException) {
				LVTTSClientException le = (LVTTSClientException) e;
				System.out.println("errorCode=" + le.getErrorCode());
			}
		} finally {
			try {
				System.out.println("invoke LV_TTS_DestroyClient");
				client.LV_TTS_DestroyClient();
			} catch (LVTTSClientException e) {
				e.printStackTrace(System.out);
			}
			System.out.println("invoke LV_SRE_Shutdown");
			LVTTSClient.LV_SRE_Shutdown();
		}
	}

	public static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

}
