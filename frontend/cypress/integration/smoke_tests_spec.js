describe("Smoke tests", () => {
  it("Performs basic search", () => {
    cy.visit("/");
    cy.findByRole("textbox", { name: "Search query" }).type("art");
    cy.findByRole("textbox", { name: "Root catalog" }).type("/app/files");
    cy.findByRole("button", { name: "Search" }).click();
    cy.findByText("/app/files/sample.md").should("exist");
    cy.findByText("/app/files/sample.txt").should("exist");
    cy.findByText("/app/files/bells.odt").should("exist");
  });
});
