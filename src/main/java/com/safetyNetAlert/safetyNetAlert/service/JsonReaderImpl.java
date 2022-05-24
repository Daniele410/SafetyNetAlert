package com.safetyNetAlert.safetyNetAlert.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class JsonReaderImpl {

	public JsonReaderImpl() throws IOException {

		ObjectMapper mapper = new ObjectMapper();

		JsonNode root = mapper.readTree(new File("src\\main\\resources\\data.json"));

		// Get Person
		JsonNode personsNode = root.path("person");
		if (personsNode.isArray()) {
			System.out.println("Is this node an Array?" + personsNode.isArray());

			for (JsonNode nodePersons : personsNode) {
				String firstName = nodePersons.path("firstName").asText();
				System.out.println("First Name: " + firstName);

				String lastName = nodePersons.path("lastName").asText();
				System.out.println("Last Name: " + lastName);

				String address = nodePersons.path("address").asText();
				System.out.println("Address: " + address);

				String city = nodePersons.path("city").asText();
				System.out.println("City: " + city);

				int zip = nodePersons.path("zip").asInt();
				System.out.println("Zip: " + zip);

				String phone = nodePersons.path("phone").asText();
				System.out.println("Phone: " + phone);

				String email = nodePersons.path("email").asText();
				System.out.println("Email: " + email);

				// Get Firestations
				JsonNode firestationsNode = root.path("firestations");
				if (firestationsNode.isArray()) {
					System.out.println("Is this node an Array?" + firestationsNode.isArray());

					for (JsonNode nodeFirestations : firestationsNode) {

						String addressFirestation = nodeFirestations.path("address").asText();
						System.out.println("Address Firestation: " + addressFirestation);

						int station = nodeFirestations.path("station").asInt();
						System.out.println("Station: " + station);

						// Get Medicalrecords
						JsonNode medicalrecordsNode = root.path("medicalrecords");
						if (medicalrecordsNode.isArray()) {
							System.out.println("Is this node an Array?" + medicalrecordsNode.isArray());

							for (JsonNode nodeMedicalrecords : medicalrecordsNode) {
								String firstNameMedicalrecord = nodeMedicalrecords.path("firstName").asText();
								System.out.println("First Name: " + firstNameMedicalrecord);

								String lastNameMedicalrecord = nodeMedicalrecords.path("lastName").asText();
								System.out.println("Last Name: " + lastNameMedicalrecord);

								String birthdate = nodeMedicalrecords.path("birthdate").asText();
								System.out.println("Birthdate: " + birthdate);

								String medications = nodeMedicalrecords.path("medications").asText();
								System.out.println("Medications: " + medications);

								String allergies = nodeMedicalrecords.path("allergies").asText();
								System.out.println("Allergies: " + allergies);

							}
						}

					}

				}

			}
		}
	}
}

//				//Get Firestations
//				JsonNode firestationsNode = root.path("firestations");
//				if(firestationsNode.isArray()) {
//					System.out.println("Is this node an Array?" + firestationsNode.isArray());
//					
//					for (JsonNode nodeFirestations : firestationsNode) {
//						
//						String addressFirestations = nodeFirestations.path("address").asText();
//						int station = nodeFirestations.path("station").asInt();

//		JSONParser parser = new JSONParser();

//
//		String dataFile = "src\\main\\resources\\data.json";
//
//           
//            Object obj = (ObjectMapper) parser.parse(new FileReader(dataFile));
//
//            JSONObject jsonObject =  (JSONObject) obj;
//            
//            
//
//            String firstName = (String) jsonObject.get("firstName");
//            System.out.println(firstName);
//
//            String lastName = (String) jsonObject.get("lastName");
//            System.out.println(lastName);
//
//            String address = (String) jsonObject.get("address");
//            System.out.println(address);
//            
//            String city = (String) jsonObject.get("city");
//            System.out.println(city);
//            
//            String zip = (String) jsonObject.get("zip");
//            System.out.println(zip);
//            
//            String phone = (String) jsonObject.get("phone");
//            System.out.println(phone);
//            
//            String email = (String) jsonObject.get("email");
//            System.out.println(email);
//            
//            int station = (int) jsonObject.get("station");
//            System.out.println(station);
//            
//            String birthdate = (String) jsonObject.get("birthdate");
//            System.out.println(birthdate);
//            
//            String medications = (String) jsonObject.get("medications");
//            System.out.println(medications);
//            
//            String allergies = (String) jsonObject.get("allergies");
//            System.out.println(allergies);
//            
//            
//            
//
//            // loop array
//            
//            
//            
//            
//            JSONArray medicalrecords = (JSONArray) jsonObject.get("medicalrecords");
//            Iterator<?> iterator = medicalrecords.iterator();
//            while (iterator.hasNext()) {
//             System.out.println(iterator.next());
//            }

//	public void JsonReader(String fileName) {
//		this.fileName = fileName;
//	}
//
//	public void loadData() throws FileNotFoundException, IOException, ParseException {
//		org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
//		Object obj = parser.parse(new FileReader(this.fileName));
//		JSONObject jsonObject = (JSONObject) obj;
//	}

//		JsonReaderImpl jsonReaderImpl = new JsonReaderImpl("data.json"); 
// parsing file "JSONExample.json"

//		JSONParser parser = new JSONParser("FileReader");
//		
//    Object obj = parser.parse(new FileReader("data.json"));
//
//    // typecasting ob to JSONObject
//    JSONObject js = (JSONObject) obj;
//
//    String firstName = (String) js.get("firstName");
//    String lastName = (String) js.get("lastName");
//
//    System.out.println("First name is: " + firstName);
//    System.out.println("Last name is: " +lastName);

//private static ObjectMapper objectMapper = getDefaultObjectMapper();
//	
//	private static ObjectMapper getDefaultObjectMapper() {
//		
//		ObjectMapper defaultObjectMapper = new ObjectMapper();
//		
//		return defaultObjectMapper;
//		
//	}
//	
//	public static JsonNode perse(String src) throws JsonMappingException, JsonProcessingException {
//		
//		return objectMapper.readTree(src);
//	}

// Lire le fichier Json, comment récupérer la donnée d'un fichier

// extraire les persons
//		loadPersons(nodePersons)
// extraire les firestations
// extraires les medicalRecords
