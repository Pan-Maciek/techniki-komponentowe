import React, { useState } from "react";
import {
  Collapse,
  Grid,
  IconButton,
  Paper,
  TextField,
  FormGroup,
  FormControlLabel,
  Checkbox,
  Divider,
} from "@mui/material";
import cx from "classnames";
import styles from "./Search.module.scss";
import {
  FilterList as FilterListIcon,
  FolderOpen as FolderIcon,
  Search as SearchIcon,
} from "@mui/icons-material";
import { SubmitHandler, useForm } from "react-hook-form";
import {
  SearchParams,
  SearchResponse,
  SERVICES,
  LANGUAGES,
  FORMS,
} from "../../commons/types";

type SearchFormProps = {
  onSubmit: SubmitHandler<SearchParams>;
};

const serviceLabelMap: Record<keyof SearchResponse, string> = {
  "text-search": "Plain text",
  "odt-search": "ODT",
  "pdf-search": "PDF",
  "microsoft-search": "Microsoft (DOCX, PPTX, XLS)",
  "audio-search": "Audio files",
  "video-search": "Video files (audio)",
  "video-search-ocr": "Video files (image)",
  "ocr-search": "Image files",
  "archive-search": "Archive files",
};

const languageLabelMap: Record<typeof LANGUAGES[number], string> = {
  en: "English",
  de: "German",
};

const formLabelMap: Record<typeof FORMS[number], string> = {
  synonyms: "Synonyms",
  forms: "Forms",
  typos: "Typos",
};

export const SearchForm: React.VFC<SearchFormProps> = ({ onSubmit }) => {
  const [filtersExpanded, setFiltersExpanded] = useState<boolean>(false);
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<SearchParams>();

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <Paper
        elevation={8}
        sx={{ p: 2 }}
        className={cx({
          [styles.borderRadiusTopOnly]: filtersExpanded,
        })}
      >
        <Grid container spacing={2}>
          <Grid item xs={6}>
            <TextField
              id="search-query"
              label="Search query"
              variant="outlined"
              fullWidth
              error={!!errors.phrase}
              helperText={errors.phrase?.message ?? "Phrase in Polish"}
              {...register("phrase", {
                required: "Field must not be empty",
              })}
            />
          </Grid>
          <Grid item xs={4}>
            <TextField
              id="root-catalog"
              label="Root catalog"
              variant="outlined"
              fullWidth
              error={!!errors.rootPath}
              helperText={errors.rootPath?.message ?? 'E.g. "/home/user/"'}
              InputProps={{
                endAdornment: <FolderIcon />,
              }}
              {...register("rootPath", {
                required: "Field must not be empty",
              })}
            />
          </Grid>
          <Grid
            item
            xs={1}
            display="flex"
            justifyContent="center"
            alignItems="flex-start"
          >
            <IconButton
              size="large"
              onClick={() => setFiltersExpanded(!filtersExpanded)}
              aria-label={filtersExpanded ? "Hide filters" : "Show filters"}
            >
              <FilterListIcon fontSize="inherit" />
            </IconButton>
          </Grid>
          <Grid
            item
            xs={1}
            display="flex"
            justifyContent="center"
            alignItems="flex-start"
          >
            <IconButton size="large" type="submit" aria-label="Search">
              <SearchIcon fontSize="inherit" />
            </IconButton>
          </Grid>
        </Grid>
      </Paper>
      <Collapse in={filtersExpanded}>
        <Paper
          elevation={4}
          sx={{ p: 2 }}
          className={cx({
            [styles.borderRadiusBottomOnly]: filtersExpanded,
          })}
        >
          <Grid container spacing={2}>
            <Grid item xs={12}>
              <h4 className={styles.formHeader}>File types</h4>
              <Divider />
              <FormGroup row>
                {SERVICES.map((service, i) => (
                  <FormControlLabel
                    key={service}
                    control={<Checkbox defaultChecked />}
                    label={serviceLabelMap[service]}
                    value={service}
                    {...register(`additionalInfo.enabledFormats.${i}`)}
                  />
                ))}
              </FormGroup>
            </Grid>
            <Grid item xs={12}>
              <h4 className={styles.formHeader}>Languages (except Polish)</h4>
              <Divider />
              <FormGroup row>
                {LANGUAGES.map((language, i) => (
                  <FormControlLabel
                    key={language}
                    control={<Checkbox defaultChecked />}
                    label={languageLabelMap[language]}
                    value={language}
                    {...register(`additionalInfo.lang.${i}`)}
                  />
                ))}
              </FormGroup>
            </Grid>
            <Grid item xs={12}>
              <h4 className={styles.formHeader}>Search modes</h4>
              <Divider />
              <FormGroup row>
                {FORMS.map((form, i) => (
                  <FormControlLabel
                    key={form}
                    control={<Checkbox defaultChecked />}
                    label={formLabelMap[form]}
                    value={form}
                    {...register(`additionalInfo.forms.${i}`)}
                  />
                ))}
              </FormGroup>
            </Grid>
          </Grid>
        </Paper>
      </Collapse>
    </form>
  );
};
