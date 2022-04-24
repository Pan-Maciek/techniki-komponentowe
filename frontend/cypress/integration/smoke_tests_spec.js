describe("Smoke tests", () => {
  it("Finds plain text files", () => {
    cy.visit("/");
    cy.findByRole("textbox", { name: "Search query" }).type("art");
    cy.findByRole("textbox", { name: "Root catalog" }).type("/app/files");
    cy.findByRole("button", { name: "Search" }).click();
    cy.findByText("/app/files/sample.md").should("exist");
    cy.findByText("/app/files/sample.txt").should("exist");
  });

  it("Finds ODT files", () => {
    cy.visit("/");
    cy.findByRole("textbox", { name: "Search query" }).type("bells");
    cy.findByRole("textbox", { name: "Root catalog" }).type("/app/files");
    cy.findByRole("button", { name: "Search" }).click();
    cy.findByText("/app/files/bells.odt").should("exist");
  });

  it("Finds PDF files", () => {
    cy.visit("/");
    cy.findByRole("textbox", { name: "Search query" }).type("bomb");
    cy.findByRole("textbox", { name: "Root catalog" }).type("/app/files");
    cy.findByRole("button", { name: "Search" }).click();
    cy.findByText("/app/files/ktane-manual.pdf").should("exist");
  });

  it("Finds only specified formats", () => {
    cy.visit("/");
    cy.findByRole("textbox", { name: "Search query" }).type("art");
    cy.findByRole("textbox", { name: "Root catalog" }).type("/app/files");
    cy.findByRole("button", { name: "Show filters" }).click();
    cy.findByRole("checkbox", { name: "ODT" }).click();
    cy.findByRole("checkbox", { name: "PDF" }).click();
    cy.findByRole("button", { name: "Search" }).click();
    cy.findByText("/app/files/sample.md").should("exist");
    cy.findByText("/app/files/sample.txt").should("exist");
    cy.findByText("/app/files/bells.odt").should("not.exist");
  });
});
