namespace TextSearch.Misc; 

public static class StringHelper {
    public static List<int>? IndicesOfAny(this string input, List<string> values, StringComparison comparisonType)
    {
        var index = input.IndexOfAny(values, 0, comparisonType);
        if (index == -1) return null;
        var indices = new List<int>();
        
        while (index >= 0 && index < input.Length) {
            indices.Add(index);
            index = input.IndexOfAny(values, index + 1, comparisonType);
        }

        return indices;
    }

    public static int IndexOfAny(this string input, IEnumerable<string> values, int startIndex, StringComparison comparisonType) =>
        values.Select(value => input.IndexOf(value, startIndex, comparisonType))
              .Where(index => index > -1)
              .FirstOrDefault(-1);
}