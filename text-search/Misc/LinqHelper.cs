namespace TextSearch.Misc;

public static class LinqHelper {
    public static IEnumerable<T> WhereNotNull<T>(this IEnumerable<T?> source) => 
        source.Where(entry => entry is not null)!;
}