package com.lumenvox.tts;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class WaveOutputStream {
	public static final int AUDIO_FORMAT_UNKNOWN = 0;
	public static final int AUDIO_FORMAT_ULAW = 1;
	public static final int AUDIO_FORMAT_PCM = 2;
	public static final int AUDIO_FORMAT_ALAW = 3;

	public static final int CHANNEL_MONO = 1;
	public static final int CHANNEL_STEREO = 2;

	public static final int SAMPLING_RATE_8000Hz = 8000;
	public static final int SAMPLING_RATE_22050Hz = 22050;

	private int mSampleRate;
	private int mChannels;
	private int mSampleBits;

	private int mBytesWritten;

	private int audioFormat;

	private DataOutputStream out;

	/**
	 * Constructor; initializes WavFile with file name and path
	 * 
	 * @param sampleRate
	 *            output sample rate
	 * @param channels
	 *            number of channels
	 * @param audioFormat
	 *            AUDIO_FORMAT_ALAW, AUDIO_FORMAT_ULAW
	 */
	public WaveOutputStream(OutputStream out, int sampleRate, int channels, int audioFormat) {
		this.out = new DataOutputStream(out);
		this.mSampleRate = sampleRate;
		this.mChannels = channels;
		this.mSampleBits = this.getBitsPerSample(audioFormat);
		this.audioFormat = this.getAudioFormat(audioFormat);
		this.mBytesWritten = 0;
	}

	private void writeWaveHeader() throws IOException {
		if (out != null) {
			int bytesPerSec = (mSampleBits + 7) / 8;
			out.writeBytes("RIFF"); // WAV chunk header
			out.writeInt(Integer.reverseBytes(mBytesWritten + 36)); // WAV
																	// chunk
																	// //
			// size
			out.writeBytes("WAVE"); // WAV format
			out.writeBytes("fmt "); // format subchunk header
			out.writeInt(Integer.reverseBytes(16)); // format subchunk size
			out.writeShort(Short.reverseBytes((short) audioFormat)); // audio
																		// format
			out.writeShort(Short.reverseBytes((short) mChannels)); // number
																	// of
																	// //
			// channels
			out.writeInt(Integer.reverseBytes(mSampleRate)); // sample rate
			out.writeInt(Integer.reverseBytes(mSampleRate * mChannels * bytesPerSec)); // byte
			// rate
			out.writeShort(Short.reverseBytes((short) (mChannels * bytesPerSec))); // block
			// align
			out.writeShort(Short.reverseBytes((short) mSampleBits)); // bits
																		// per
																		// sample
			this.out.writeBytes("data"); // data subchunk header
			this.out.writeInt(Integer.reverseBytes(mBytesWritten)); // data
																	// subchunk
																	// size
		}
	}

	public void write(byte[] src) throws IOException {
		this.write(src, 0, src.length);
	}

	public void write(byte[] src, int offset, int length) throws IOException {
		if (mChannels != 1) {
			return;
		}
		if (offset > length) {
			throw new IndexOutOfBoundsException(String.format("offset %d is greater than length %d", offset, length));
		}
		if (out != null) {
			mBytesWritten = length - offset;
			writeWaveHeader();
			out.write(src, offset, length);
			out.flush();
		}
	}

	public void write(byte[] left, byte[] right, int offset, int length) throws IOException {
		if (mChannels != 2) {
			return;
		}
		if (offset > length) {
			throw new IndexOutOfBoundsException(String.format("offset %d is greater than length %d", offset, length));
		}
		if (out != null) {
			mBytesWritten = (length - offset) * 2;
			writeWaveHeader();
			for (int i = offset; i < length; i++) {
				this.out.write(left[i]);
				this.out.write(right[i]);
			}
			out.flush();
		}
	}

	private int getAudioFormat(int output_audio_format) {
		int RIFF_FORMAT_PCM = 1;
		int RIFF_FORMAT_ALAW = 6;
		int RIFF_FORMAT_MULAW = 7;
		if (output_audio_format == AUDIO_FORMAT_PCM) {
			return RIFF_FORMAT_PCM;
		} else if (output_audio_format == AUDIO_FORMAT_ALAW) {
			return RIFF_FORMAT_ALAW;
		} else if (output_audio_format == AUDIO_FORMAT_ULAW) {
			return RIFF_FORMAT_MULAW;
		}
		return RIFF_FORMAT_PCM;
	}

	private int getBitsPerSample(int output_audio_format) {
		if (output_audio_format == AUDIO_FORMAT_PCM) {
			return 16;
		} else if (output_audio_format == AUDIO_FORMAT_ALAW) {
			return 8;
		} else if (output_audio_format == AUDIO_FORMAT_ULAW) {
			return 8;
		}
		return 16;
	}

	public void flush() throws IOException {
		if (out != null) {
			out.flush();
		}
	}

	public void close() throws IOException {
		if (out != null) {
			out.close();
		}
	}

}
