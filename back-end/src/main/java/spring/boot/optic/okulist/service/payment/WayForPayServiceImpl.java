package spring.boot.optic.okulist.service.payment;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import spring.boot.optic.okulist.dto.payment.WayForPayRequestDto;
import spring.boot.optic.okulist.dto.payment.WayForPayResponseDto;
import spring.boot.optic.okulist.exception.WayForPayApiException;

@Service
@RequiredArgsConstructor
public class WayForPayServiceImpl implements WayForPayService {
    private final CloseableHttpClient httpClient;

    @Override
    public WayForPayResponseDto pay(WayForPayRequestDto dto) {
        HttpPost request = new HttpPost("https://api.wayforpay.com/api/pay");
        // Set request parameters, if any

        try {
            HttpResponse response = httpClient.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                String responseBody = EntityUtils.toString(entity);
                ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(responseBody, WayForPayResponseDto.class);
            } else {
                throw new WayForPayApiException("WayForPay API error: " + statusCode);
            }
        } catch (IOException e) {
            throw new WayForPayApiException("Error processing WayForPay API response");
        }
    }
}
