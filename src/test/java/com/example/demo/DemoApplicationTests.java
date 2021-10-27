package com.example.demo;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import com.example.demoMaven.dao.CartT;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.core.importer.Location;

@ActiveProfiles("unittest")
@Testcontainers(disabledWithoutDocker = true)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
	private static final Logger logger = LoggerFactory.getLogger(DemoApplicationTests.class);

  @Container
  private static final PostgreSQLContainer database =
      new PostgreSQLContainer("postgres:13.3")
          .withDatabaseName("testdb");

  @Autowired private TestRestTemplate restTemplate;

  @Value("${apiDocPath}")
  private String apiDocPath;

  @DynamicPropertySource
  static void mysqlProperties(DynamicPropertyRegistry registry) {
	  logger.debug(
        "url={}, username={}, password={}",
        database.getJdbcUrl(),
        database.getUsername(),
        database.getPassword());
    registry.add("spring.datasource.url", database::getJdbcUrl);
    registry.add("spring.datasource.username", database::getUsername);
    registry.add("spring.datasource.password", database::getPassword);
  }

  @Test
  @DisplayName("Specification to html generator 😱")
  public void openapi() throws Exception {
    //
    Path openapiFile = Paths.get(apiDocPath);
    // Path openapiFile = Paths.get("swagger", "api.json");
    openapiFile.toFile().getParentFile().mkdirs();
    //
    UriComponents uriComponents = UriComponentsBuilder.fromUriString("/v2/api-docs").build();
    ResponseEntity<String> responseEntity = null;

    // HttpHeaders headers = new HttpHeaders();
    // headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    // // headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    // HttpEntity<String> entity = new HttpEntity<>(headers);

    try {
      responseEntity =
          restTemplate.exchange(uriComponents.getPath(), HttpMethod.GET, null, String.class);
    } catch (Exception e) {
    	logger.error("uri={}, responseEntity={}", uriComponents.getPath(), responseEntity, e);
    }
    try (BufferedWriter writer = Files.newBufferedWriter(openapiFile, StandardCharsets.UTF_8)) {
      writer.write(responseEntity.getBody());
    }
    // 將 openapi 輸出到 build/asciidoc/swagger.json

    // Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
    // // .withMarkupLanguage(MarkupLanguage.ASCIIDOC)
    // // .withOutputLanguage(Language.EN)
    // // .withPathsGroupedBy(GroupBy.TAGS).withGeneratedExamples()
    // // .withoutInlineSchema()
    // .build();
    // System.out.println(config);
    // System.out.println("isBasePathPrefixEnabled=" +
    // config.isBasePathPrefixEnabled());
    // System.out.println("isFlatBodyEnabled=" + config.isFlatBodyEnabled());
    // System.out.println("isGeneratedExamplesEnabled=" +
    // config.isGeneratedExamplesEnabled());
    // System.out.println("isInlineSchemaEnabled=" +
    // config.isInlineSchemaEnabled());
    // System.out.println("isInterDocumentCrossReferencesEnabled="
    // + config.isInterDocumentCrossReferencesEnabled());
    // System.out.println("isListDelimiterEnabled=" +
    // config.isListDelimiterEnabled());
    // System.out.println("isPathSecuritySectionEnabled=" +
    // config.isPathSecuritySectionEnabled());
    // System.out.println("isSeparatedDefinitionsEnabled=" +
    // config.isSeparatedDefinitionsEnabled());
    // System.out.println("isSeparatedOperationsEnabled=" +
    // config.isSeparatedOperationsEnabled());
    // // // 轉成 swagger.adoc
    // Swagger2MarkupConverter converter =
    // Swagger2MarkupConverter.from(openapiTempFile).withConfig(config).build();
    // converter.toFile(Paths.get("build/generated-snippets/index.adoc"));
    // AsciiDocBuilder b = new AsciiDocBuilder()

    // log.info("openapi={}", responseEntity.getBody());
    assertNotNull(responseEntity.getBody());
  }

    @Test
  public void get_cart_test() throws Exception {
	  logger.info("some input ex cartId={}", "001");
	  
	     System.out.printf("msg ssss");
    UriComponents uriComponents = UriComponentsBuilder.fromUriString("/carts/{cartId}").build();
    uriComponents = uriComponents.expand(Collections.singletonMap("cartId", "001"));
    ResponseEntity<CartT> responseEntity = null;
    try {
      responseEntity =
          restTemplate.exchange(uriComponents.getPath(), HttpMethod.GET, null, CartT.class);
    } catch (Exception e) {
    	logger.error("uri={}, responseEntity={}", uriComponents.getPath(), responseEntity, e);
    }
    logger.info(
        "uri={}, statusCode={}, CartInfoDto={}",
        uriComponents.getPath(),
        responseEntity.getStatusCode(),
        responseEntity.getBody());
    Assert.assertEquals(responseEntity.getBody().getCustomerName(), "sam");
  }

  //   @Test
  public void file_name_and_package_name_and_architecture_rule() {
    JavaClasses importedClasses =
        new ClassFileImporter()
            // exclude test
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            // exclude jar
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_JARS)
            // exclude shareddomain
            .withImportOption(
                new ImportOption() {
                  @Override
                  public boolean includes(Location location) {
                    return !location.contains("/shareddomain/");
                  }
                })
            // package source
            .importPackages("com.example.demo");
    // package & file name rule
   
  }
}
