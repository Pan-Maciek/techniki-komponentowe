describe("Smoke tests", () => {
  const checkOnly = (option) => {
    const options = ["Plain text", "ODT", "PDF", "Audio files"];
    options
      .filter((opt) => opt !== option)
      .forEach((opt) => cy.findByRole("checkbox", { name: opt }).click());
  };

  it("Finds plain text files", () => {
    cy.visit("/");
    cy.findByRole("textbox", { name: "Search query" }).type("art");
    cy.findByRole("textbox", { name: "Root catalog" }).type("/app/files");

    cy.findByRole("button", { name: "Show filters" }).click();
    checkOnly("Plain text");

    cy.findByRole("button", { name: "Search" }).click();

    cy.findByText("/app/files/sample.md").should("exist");
    cy.findByText("/app/files/sample.txt").should("exist");
  });

  it("Finds ODT files", () => {
    cy.visit("/");
    cy.findByRole("textbox", { name: "Search query" }).type("bells");
    cy.findByRole("textbox", { name: "Root catalog" }).type("/app/files");

    cy.findByRole("button", { name: "Show filters" }).click();
    checkOnly("ODT");

    cy.findByRole("button", { name: "Search" }).click();

    cy.findByText("/app/files/bells.odt").should("exist");
  });

  it("Finds PDF files", () => {
    cy.visit("/");
    cy.findByRole("textbox", { name: "Search query" }).type("bomb");
    cy.findByRole("textbox", { name: "Root catalog" }).type("/app/files");

    cy.findByRole("button", { name: "Show filters" }).click();
    checkOnly("PDF");

    cy.findByRole("button", { name: "Search" }).click();

    cy.findByText("/app/files/ktane-manual.pdf").should("exist");
  });

  it("Finds audio files", () => {
    cy.visit("/");
    cy.findByRole("textbox", { name: "Search query" }).type("kolor");
    cy.findByRole("textbox", { name: "Root catalog" }).type("/app/files");

    cy.findByRole("button", { name: "Show filters" }).click();
    checkOnly("Audio files");

    cy.findByRole("button", { name: "Search" }).click();

    cy.findByText("/app/files/kret_d044.wav").should("exist");
  });

  it("Finds files in subfolders", () => {
    cy.visit("/");
    cy.findByRole("textbox", { name: "Search query" }).type("colour");
    cy.findByRole("textbox", { name: "Root catalog" }).type("/app/files");

    cy.findByRole("button", { name: "Show filters" }).click();
    checkOnly("PDF");

    cy.findByRole("button", { name: "Search" }).click();

    cy.findByRole("treeitem", { name: "subfolder" }).click();
    cy.findByText("/app/files/subfolder/death_of_all_colours.pdf").should(
      "exist"
    );
  });

  it("Finds translated phrase", () => {
    cy.visit("/");
    cy.findByRole("textbox", { name: "Search query" }).type("serce");
    cy.findByRole("textbox", { name: "Root catalog" }).type("/app/files");

    cy.findByRole("button", { name: "Show filters" }).click();
    checkOnly("ODT");

    cy.findByRole("button", { name: "Search" }).click();

    cy.findByText("/app/files/bells.odt").should("exist");
    cy.findByText("/app/files/mein_herz_brennt.odt").should("exist");
  });
});
