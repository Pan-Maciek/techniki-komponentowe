import React from "react";
import { Alert, AlertTitle } from "@mui/material";
import styles from "./Search.module.scss";

export const SearchError: React.VFC<{
  service: string;
  errors: string[];
}> = ({ service, errors }) =>
  errors.length > 0 ? (
    <Alert
      severity="error"
      sx={{
        my: 2,
        p: 2,
      }}
    >
      <AlertTitle>
        Error{errors.length > 1 && "s"} from <strong>{service}</strong>
      </AlertTitle>
      {errors.length === 1 ? (
        <em>
          <pre className={styles.error}>{errors[0]}</pre>
        </em>
      ) : (
        <ul>
          {errors.map((error) => (
            <li>
              <em>
                <pre className={styles.error}>{error}</pre>
              </em>
            </li>
          ))}
        </ul>
      )}
    </Alert>
  ) : null;
