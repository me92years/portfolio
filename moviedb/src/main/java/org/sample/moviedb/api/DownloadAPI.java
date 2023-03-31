package org.sample.moviedb.api;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DownloadAPI {

	@Autowired
	private ResourceLoader resourceLoader;
	
	@GetMapping("/thumbnail")
	public ResponseEntity<byte[]> thumbnail(String img) throws IOException, URISyntaxException {
		try {
			Resource resource = resourceLoader.getResource("classpath:/static/img");
			String uri = URLDecoder.decode(img, "utf-8");
			String url = resource.getURL() + uri;
			Path path = Path.of(new URI(url));
			File file = path.toFile();
			HttpHeaders header = new HttpHeaders();
			header.add("Content-Type", Files.probeContentType(path));
			return new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
