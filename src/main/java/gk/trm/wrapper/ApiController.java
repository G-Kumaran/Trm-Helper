package gk.trm.wrapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
class ApiController
{
	@Value("${trm.address:localhost}")
	private String ip;

	@Value("${trm.port:4028}")
	private int port;

	@GetMapping("/{command}")
	public String getApi(@PathVariable
	String command)
	{
		return ApiReader.get(ip, port, command);
	}

}
