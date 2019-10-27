package com.example.demo;

import java.io.IOException;
import java.util.Optional;

import io.jenetics.jpx.GPX;
import io.jenetics.jpx.Metadata;
import io.jenetics.jpx.Track;
import io.jenetics.jpx.TrackSegment;

public class ParseGPX {
	public static void main(String[] args) {
		try {
			GPX read = GPX.read("sample/sample.gpx");
			read.tracks().flatMap(Track::segments).flatMap(TrackSegment::points).forEach(System.out::println);
			Optional<String> flatMap = read.getMetadata().flatMap(Metadata::getDescription);
			System.out.println("getDescription" + flatMap.get());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
