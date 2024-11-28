package com.gs.Receipt_Reward_Backend;

import com.gs.Receipt_Reward_Backend.model.Receipt;
import com.gs.Receipt_Reward_Backend.model.ReceiptIdResponse;
import com.gs.Receipt_Reward_Backend.model.ReceiptItem;
import com.gs.Receipt_Reward_Backend.model.ReceiptPointsResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReceiptRewardBackendApplicationTests {

	@LocalServerPort
	private int port;

	private TestRestTemplate restTemplate;

	@Test
	public void testEndpoints() {
		restTemplate = new TestRestTemplate();

		UUID receiptId = UUID.randomUUID();

		//Test for receipt id that does not exist, should return 404 and an error message
		ResponseEntity<String> response1 = restTemplate.getForEntity(
				"http://localhost:" + port + "/receipts/" + receiptId + "/points",
				String.class
		);

		assertEquals(HttpStatusCode.valueOf(404), response1.getStatusCode());
        assertTrue(Objects.requireNonNull(response1.getBody()).contains(receiptId.toString()));

		//Test for accepting a receipt
		ResponseEntity<String> response2 = restTemplate.postForEntity(
				"http://localhost:" + port + "/receipts/process",
				buildReceipt(),
				String.class
		);

		assertEquals(HttpStatusCode.valueOf(201), response2.getStatusCode());
		assertTrue(Objects.requireNonNull(response2.getBody()).contains("\"id\":"));

		String returnedReceiptId = response2.getBody().split("\"")[3];

		//Test for receipt id that exists that points are returned
		ResponseEntity<String> response3 = restTemplate.getForEntity(
				"http://localhost:" + port + "/receipts/" + returnedReceiptId + "/points",
				String.class
		);

		assertEquals(HttpStatusCode.valueOf(200), response3.getStatusCode());
		assertTrue(Objects.requireNonNull(response3.getBody()).contains("\"points\":"));
	}

	private Receipt buildReceipt() {
		return Receipt.builder()
				.retailer("Target")
				.purchaseDate("2022-01-01")
				.purchaseTime("13:01")
				.items(buildItemList())
				.total("35.35")
				.build();
	}

	private List<ReceiptItem> buildItemList() {
		return List.of(
				ReceiptItem.builder()
						.shortDescription("Mountain Dew 12PK")
						.price("6.49")
						.build(),
				ReceiptItem.builder()
						.shortDescription("Emils Cheese Pizza")
						.price("12.25")
						.build(),
				ReceiptItem.builder()
						.shortDescription("Knorr Creamy Chicken")
						.price("1.26")
						.build(),
				ReceiptItem.builder()
						.shortDescription("Doritos Nacho Cheese")
						.price("3.35")
						.build(),
				ReceiptItem.builder()
						.shortDescription("   Klarbrunn 12-PK 12 FL OZ  ")
						.price("12.00")
						.build()
		);
	}
}
