import { ApiClient } from './ApiClient';

describe('ApiClient', () => {
    let mockFetch: jest.Mock;

    beforeEach(() => {
        mockFetch = jest.fn();
        (global as any).fetch = mockFetch;
    });

    afterEach(() => {
        jest.resetAllMocks();
    });

    describe('getPosts', () => {
        it('should return an array of PostModel objects if response is successful', async () => {
            // Mock a successful response
            const mockResponse = {
                ok: true,
                json: jest.fn().mockResolvedValue([]),
            };
            mockFetch.mockResolvedValueOnce(mockResponse);

            const result = await ApiClient.getPosts();

            expect(result).toEqual([]);
            expect(mockFetch).toHaveBeenCalledWith('http://localhost:8080/postssort?filter=newest');
        });

        it('should throw an error if response is not successful', async () => {
            // Mock an unsuccessful response
            const mockResponse = {
                ok: false,
                json: jest.fn().mockResolvedValue({ error: 'Failed to fetch posts' }),
            };
            mockFetch.mockResolvedValueOnce(mockResponse);

            await expect(ApiClient.getPosts()).rejects.toThrowError('Failed to fetch posts');
            expect(mockFetch).toHaveBeenCalledWith('http://localhost:8080/postssort?filter=newest');
        });
    });


});
